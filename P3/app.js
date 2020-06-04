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

    updateGUI(accion, nombre, nombre_ant){
        console.log(accion + " " + nombre)
        let tareas = this.state.tareas
        if(accion === "add"){
            
            console.log(typeof(tareas))
            tareas.push(nombre)

            localStorage.setItem("tareas",JSON.stringify(tareas))
            this.setState({tareas: tareas})

        }else if(accion === "guardar"){
            
            console.log(typeof(tareas))
            var i = tareas.indexOf(nombre_ant);
 
            if ( i !== -1 ) {
                tareas[i] = nombre
            }
            

            console.log(tareas)

            localStorage.setItem("tareas",JSON.stringify(tareas))
            this.setState({tareas: tareas})
                
        }else if(accion === "borrar"){
            
            var i = tareas.indexOf( nombre_ant );
 
            if ( i !== -1 ) {
                tareas.splice( i, 1 );
            }

            localStorage.setItem("tareas",JSON.stringify(tareas))
            this.setState({tareas: tareas})

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
                <${mostrartareas} 
                    tareas=${this.state.tareas}
                    updateGUI=${this.updateGUI.bind(this)}
                    />
            </div>
        </main>`
    }
}


render(html`<${Principal} />`, document.body)
