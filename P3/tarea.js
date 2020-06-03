import { html, render, Component } from 'https://unpkg.com/htm/preact/standalone.module.js'

class tarea extends Component{

    constructor(props) {
        super(props)
        this.valor = {
            texto: "default",
            estado: false
        }
    }

    render(){
        if(this.valor.estado){
            return html `
                        <input type="text" value=${this.valor.texto} />
                        <div class="tarea">
                        <button onclick=${ this.cancelarClick.bind(this)} > Cancelar </button> 
                        <button onclick=${ this.guardarClick.bind(this)} > Guardar </button> 
                        <button onclick=${ this.eliminarClick.bind(this)} > Eliminar </button> 
                        </div>
                        `
        }else{
            return html `<button onclick=${ this.tareaOnClick.bind(this)} > ${this.valor.texto} </button> `
        }
    }

    tareaOnClick(e){
        e.preventDefault()
        if(this.valor.estado){
            this.valor.estado = false
        }else{
            this.valor.estado = true
        }
    }

    cancelarClick(e){

    }

    guardarClick(e){

    }

    eliminarClick(e){

    }

}

export default tarea;