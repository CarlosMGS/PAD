import { html, render, Component } from 'https://unpkg.com/htm/preact/standalone.module.js'

class addtarea extends Component{

    constructor(props){
        super(props)

       
        this.state = {
            estado : false,
            texto : "",
            updateGUI : props.updateGUI
            
        }
    }

    render(){
        if(this.state.estado){
            return html `
                        <button class="addButton" onclick=${ this.addOnClick.bind(this)} > Añadir tarea </button>
                        <div class="nuevatarea">
                        <input type="text" id="tarea" />
                        <div class="add">
                        <button onclick=${ this.cancelarClick.bind(this)} > Cancelar </button> 
                        <button onclick=${ this.guardarClick.bind(this)} > Guardar </button> 
                        </div>
                        </div>
                        `
        }else{
            return html `<button class="addButton" onclick=${ this.addOnClick.bind(this)} > Añadir tarea </button> `
        }
    }

    addOnClick(e){
        
        e.preventDefault()
        if(this.state.estado){
            this.setState({estado:false})
        }else{
            
            this.setState({estado:true})
            
        }
    }

    guardarClick(e){
        e.preventDefault()
        this.setState({texto : document.getElementById("tarea").value})
       
        if(this.state.texto !== ""){
            this.state.updateGUI("add", this.state.texto, "")
            this.setState({estado:false})
        }
    }

    cancelarClick(e){
        e.preventDefault()
        this.setState({estado:false})
    }
}

export default addtarea;