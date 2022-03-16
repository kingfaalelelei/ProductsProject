yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
    head {
        meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')
        title("View Todo")
        link(rel: "stylesheet", type: "text/css",
            href: "https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css")
    }
    body {
        div (class: 'container') {
             h1("View Todo")
                    a(href: "/todos", "Back to Todos List")
                    br()
                    br()
                    div {
                        table(border: "0") {
                            tr {
                                td("Id")
                                td(":")
                                td(todo.id ?: '')
                            }
                            tr {
                                td("Title")
                                td(":")
                                td(todo.title ?: '')
                            }
                            tr {
                                td("Completed")
                                td(":")
                                td(todo.completed)
                            }
                        }
                    }
                    br()
                    br()
                    if (allowDelete) {
                        form (id:"deleteForm", action:"/todos/$todo.id/delete", method:"POST") {
                            yield 'Delete this todo? '
                            input(type: 'submit', value: 'Yes')
                        }
                    }
                    else {
                        div {
                            a(href: "/todos/$todo.id/edit", "Edit", class: 'btn btn-secondary')
                            yield ' | '
                            a(href: "/todos/$todo.id/delete", "Delete", class: 'btn btn-danger')
                        }
                    }
                    if (errorMessage!=null) {
                        div(class: "error", "$errorMessage")
                    }
        }
    }
}