import { html, render, Component } from 'https://unpkg.com/htm/preact/standalone.module.js'
import tarea from "./tarea.js"
import addtarea from "./addtarea.js"
import mostrartareas from "./mostrartareas.js"


class Principal extends Component {

    render(){

        return html`
        <header>
            <h1 class="center">Tareas</h1>
        </header>
        <main>
            <div class="superior">
                <${addtarea}/>
            </div>
            <div class="inferior"></div>
        </main>`
    }
}


render(html`<${Principal} />`, document.body)
