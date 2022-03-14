yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
    head {
        meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')
        title("Products List")
        link(rel: "stylesheet", type: "text/css",
            href: "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css")
    }
    body {
        div (class: 'container') {

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
                     // Insert json data into database from url
                    form (id:"addForm", action:"$addProductsActionUrl", method:"POST") {
                        input(type: 'submit', value: 'Add Data', class: 'btn btn-primary')
                    }

                     if (addJsonErrorMessage != null) {
                                div(class: "error", "$addJsonErrorMessage")
                      }

                      if (successMessage != null) {
                                div(class: "error", "$successMessage")
                      }

                      br()

                    div {
                        table(border: "1", class: 'table') {
                            tr {
                                th("Id")
                                th("Name")
                                th("Description")
                                th("Price")
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
                                        p(product.description)
                                    }
                                    td {
                                        p(product.price)
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
}