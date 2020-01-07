package ec.edu.uce.optativa3.modelo;

public class Usuario {
    int id;
    String emailUsuario;
    String contraseñaUsuario;

    public Usuario() {
    }

    public String getEmailUsuario() {
        return emailUsuario;
    }

    public void setEmailUsuario(String emailUsuario) {
        this.emailUsuario = emailUsuario;
    }

    public String getContraseñaUsuario() {
        return contraseñaUsuario;
    }

    public void setContraseñaUsuario(String contraseñaUsuario) {
        this.contraseñaUsuario = contraseñaUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", emailUsuario='" + emailUsuario + '\'' +
                ", contraseñaUsuario='" + contraseñaUsuario + '\'' +
                '}';
    }

    public boolean isNull(){
        if(emailUsuario.equals("")&&contraseñaUsuario.equals("")){
            return true;
        }else{
            return false;
        }
    }

    public Usuario(String emailUsuario, String contraseñaUsuario) {
        this.emailUsuario = emailUsuario;
        this.contraseñaUsuario = contraseñaUsuario;
    }
}
