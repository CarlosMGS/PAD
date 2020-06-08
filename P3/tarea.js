import { html, render, Component } from 'https://unpkg.com/htm/preact/standalone.module.js'

class tarea extends Component{

    constructor(props) {
        super(props)
        this.state = {
            texto: props.texto,
            estado: false,
            update : props.updateGUI,
            texto_viejo: props.texto
        }
    }

    render(){
        if(this.state.estado){
            return html `
                        <div class="tareaOpt">
                        <input type="text" value=${this.state.texto} id="tarea_in"/>
                        <div class="tarea">
                        <button onclick=${ this.cancelarClick.bind(this)} > Cancelar </button> 
                        <button onclick=${ this.guardarClick.bind(this)} > Guardar </button> 
                        <button onclick=${ this.eliminarClick.bind(this)} > Eliminar </button> 
                        </div>
                        </div>
                        `
        }else{
            return html `<button onclick=${ this.tareaOnClick.bind(this)} > ${this.state.texto} </button> `
        }
    }

    tareaOnClick(e){
        e.preventDefault()
        if(this.state.estado){
            this.setState({estado:false})
        }else{
            
            this.setState({estado:true})
            
        }
    }

    cancelarClick(e){
        e.preventDefault()
        this.setState({estado:false})
    }

    guardarClick(e){
        e.preventDefault()
        this.setState({texto: document.getElementById("tarea_in").value})
        if(this.state.texto !== ""){
            this.state.update("guardar", this.state.texto, this.state.texto_viejo)
            this.setState({estado:false})
        }
    }

    eliminarClick(e){
        e.preventDefault()
        
        this.state.update("borrar", this.state.texto, this.state.texto_viejo)
        this.setState({estado:false})
        

    }

}

export default tarea;