yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
    head {
        meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')
        title("Products List")
        link(rel: "stylesheet", type: "text/css", href: "/css/bootstrap.min.css")
    }
    body {
        h1("Products List")
        div {
            nobr {
                a(href: "/products/add", "Add Product")
                yield ' | '
                a(href: "/", "Back to Index")
            }
        }
        br()
        br()
        div {
            table(border: "1") {
                tr {
                    th("Id")
                    th("Name")
                    th("Edit")
                }
                products.each { product ->
                    tr {
                        td {
                            a(href:"/products/$product.id", "$product.id")
                        }
                        td {
                            a(href:"/products/$product.id", "$product.name")
                        }
                        td {
                            a(href:"/products/$product.id/edit", "Edit")
                        }
                    }
                }
            }
        }
        br()
        br()
        div {
            nobr {
                span {
                    if (hasPrev) {
                        a(href:"/products?page=$prev", "Prev")
                        yield '   '
                    }
                }
                span {
                    if (hasNext) {
                        a(href:"/products?page=$next", "Next")
                    }
                }
            }
        }
    }
}