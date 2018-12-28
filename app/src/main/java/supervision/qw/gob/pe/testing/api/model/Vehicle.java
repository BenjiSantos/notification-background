package supervision.qw.gob.pe.testing.api.model;

public class Vehicle {
    private String Alias;
    private String Nombre;
    private String FechaDespacho;

    public Vehicle(String alias, String nombre, String fechaDespacho) {
        Alias = alias;
        Nombre = nombre;
        FechaDespacho = fechaDespacho;
    }

    public String getAlias() {
        return Alias;
    }

    public void setAlias(String alias) {
        Alias = alias;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getFechaDespacho() {
        return FechaDespacho;
    }

    public void setFechaDespacho(String fechaDespacho) {
        FechaDespacho = fechaDespacho;
    }
}
