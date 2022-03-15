yieldUnescaped '<!DOCTYPE html>'
html(lang:'en') {
    head {
        meta('http-equiv':'"Content-Type" content="text/html; charset=utf-8"')
        if (add) {
            title("Create a Todo")
        }
        else {
            title("Edit Todo")
        }
        link(rel: "stylesheet", type: "text/css", href: "/css/bootstrap.min.css")
    }
    body {
        if (add) {
            h1("Create a Todo:")
        }
        else {
            h1("Edit Todo")
        }
        a(href: "/todos", "Back to Todos List")
        br()
        br()
        form (id:"editForm", action:"$actionUrl", method:"POST") {
            table(border: "0") {
                if (!add) {
                    tr {
                        td("Id")
                        td(":")
                        td(todo.id ?: '')
                    }
                }
                tr {
                    td("Title")
                    td(":")
                    td {
                        input(name: 'title', type: 'text', value: todo.title ?: '')
                    }
                }
                if (!add) {

                tr {
                                    td("Completed")
                                    td(":")
                                    td {
                                        if (todo.completed) {
                                            input(name: 'completed', type: 'checkbox', checked: selected, value: true ?: false)
                                        } else {
                                            input(name: 'completed', type: 'checkbox', value: true ?: false)
                                        }
                                    }
                                }

                }
            }
            br()
            if (add) {
                input(type: 'submit', value: 'Create', class: 'btn btn-primary')
            }
            else {
                input(type: 'submit', value: 'Update')
            }
        }

        br()
        if (errorMessage!=null) {
            div(class: "error", "$errorMessage")
        }
    }
}