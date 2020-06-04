import { html, render, Component } from 'https://unpkg.com/htm/preact/standalone.module.js'
import tarea from './tarea.js'

class mostrartareas extends Component{
    render(){
        return this.props.tareas.map( (nombre)=> (
            html `
                <${tarea} 
                    texto=${nombre}
                    updateGUI=${this.props.updateGUI.bind(this)}/>
            `
        ))
    }
}

export default mostrartareas;