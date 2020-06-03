import { html, render, Component } from 'https://unpkg.com/htm/preact/standalone.module.js'

class addtarea extends Component{

    constructor(props){
        super(props)
        this.state = {
            estado : false,
            texto : ""
        }
    }

    render(){
        if(this.state.estado){
            return html `
                        <button onclick=${ this.addOnClick.bind(this)} > Añadir tarea </button>
                        <div class="nuevatarea">
                        <input type="text" value=${this.state.texto} />
                        <div class="add">
                        <button onclick=${ this.cancelarClick.bind(this)} > Cancelar </button> 
                        <button onclick=${ this.guardarClick.bind(this)} > Guardar </button> 
                        </div>
                        </div>
                        `
        }else{
            return html `<button onclick=${ this.addOnClick.bind(this)} > Añadir tarea </button> `
        }
    }

    addOnClick(e){
        
        e.preventDefault()
        if(this.state.estado){
            this.setState({estado:false})
        }else{
            console.log("Ha llegado con estado "+ this.state.estado)
            this.setState({estado:true})
            console.log("Ha llegado con estado "+ this.state.estado)
        }
    }

    guardarClick(e){

    }

    cancelarClick(e){
        this.setState({estado:false})
    }
}

export default addtarea;