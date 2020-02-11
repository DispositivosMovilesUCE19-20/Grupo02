package ec.edu.uce.optativa3.modelo;

public class Logs {
    private int id;
    private String usuario;
    private String inicio;
    private String fin;
    private String modelo;
    private String androidVersion;

    public Logs() {
    }

    public Logs(String usuario, String inicio, String fin, String modelo, String androidVersion) {
        this.usuario = usuario;
        this.inicio = inicio;
        this.fin = fin;
        this.modelo = modelo;
        this.androidVersion = androidVersion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getInicio() {
        return inicio;
    }

    public void setInicio(String inicio) {
        this.inicio = inicio;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAndroidVersion() {
        return androidVersion;
    }

    public void setAndroidVersion(String androidVersion) {
        this.androidVersion = androidVersion;
    }

    @Override
    public String toString() {
        return "Logs{" +
                "id=" + id +
                ", usuario='" + usuario + '\'' +
                ", inicio='" + inicio + '\'' +
                ", fin='" + fin + '\'' +
                ", modelo='" + modelo + '\'' +
                ", androidVersion='" + androidVersion + '\'' +
                '}';
    }
}
