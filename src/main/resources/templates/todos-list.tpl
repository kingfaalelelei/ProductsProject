yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
    head {
        meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')
        title("Todos List")
        link(rel: "stylesheet", type: "text/css",
        href: "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css")
    }
    body {
        div (class: 'container') {
        h1("Todos List")
                div {
                    nobr {
                        a(href: "/todos/add", "Add Todo")
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
                            th("Title")
                            th("Completed")
                            th("Edit")
                        }
                        todos.each { todo ->
                            tr {
                                td {
                                    a(href:"/todos/$todo.id", "$todo.id")
                                }
                                td {
                                    a(href:"/todos/$todo.id", "$todo.title")
                                }
                                td("$todo.completed")
                                td {
                                    a(href:"/todos/$todo.id/edit", "Edit")
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
                                a(href:"/todos?page=$prev", "Prev")
                                yield '   '
                            }
                        }
                        span {
                            if (hasNext) {
                                a(href:"/todos?page=$next", "Next")
                            }
                        }
                    }
                }
        }
    }
}