import { html, render, Component } from 'https://unpkg.com/htm/preact/standalone.module.js'
import tarea from "./tarea.js"
import addtarea from "./addtarea.js"
import mostrartareas from "./mostrartareas.js"


class Principal extends Component {

    constructor(props){
        super(props)

        this.state = {
            tareas : []
        }

        //console.log(typeof(this.state.tareas))

        if(localStorage.getItem("tareas")===null){
            localStorage.setItem("tareas", JSON.stringify([]))
            this.state.tareas = JSON.parse(localStorage.getItem("tareas"))
            
        }else{
            this.state.tareas = JSON.parse(localStorage.getItem("tareas"))
        }

        //this.updateGUI.bind(this)
    }

    updateGUI(accion, nombre){
        console.log(accion + " " + nombre)
        let tareas = this.state.tareas
        if(accion === "add"){
            /*this.setState({
                tareas: this.state.tareas.concat([nombre])
            })*/
            console.log(typeof(tareas))
            tareas.push(nombre)
            
            localStorage.setItem("tareas",JSON.stringify(tareas))
            this.state.tareas = tareas
        }else if(accion === "borrar"){

        }else{
            console.log("Error")
        }
    }

    render(){

        return html`
        <header>
            <h1 class="center">Tareas</h1>
        </header>
        <main>
            <div class="superior">
                <${addtarea} updateGUI=${this.updateGUI.bind(this)}/>
            </div>
            <div class="inferior">
                <${mostrartareas} tareas=${this.state.tareas}/>
            </div>
        </main>`
    }
}


render(html`<${Principal} />`, document.body)
