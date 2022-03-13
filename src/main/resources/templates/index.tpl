yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
    head {
        meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')
        title("$title")
        link(rel: "stylesheet", type: "text/css", href: "/css/bootstrap.min.css")
    }
    body {
        h1 ("$title")
        a(href: "/products", "Products List")
        br()
        br()
        div ("Copyright &copy; thesamoanppprogrammer.com")
    }
}