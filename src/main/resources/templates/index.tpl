yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
    head {
        meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')
        title("$title")
        link(rel: "stylesheet", type: "text/css",
            href: "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css")
    }
    body {
        div (class: 'container') {
             h1 ("Learning Spring")
                    a(href: "/todos", "Todos List")
                     br()
                     br()
                    a(href: "/products", "Products List")
//                    br()
//                    br()
                    //div ("Copyright &copy; thesamoanppprogrammer.com")
        }
    }
}