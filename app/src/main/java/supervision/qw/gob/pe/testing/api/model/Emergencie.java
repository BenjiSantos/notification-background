package supervision.qw.gob.pe.testing.api.model;

import java.lang.reflect.Array;
import java.util.List;

public class Emergencie {
    private String NumeroParte;
    private String FechaParte;
    private String CodigoTipoEmergencia;
    private String TipoEmergencia;
    private String TipoEmergenciaCompleto;
    private String Direccion;
    private String NumeroDireccion;
    private String Distrito;
    private String Ubigeo;
    private String NumeroTelefono;
    private String CodigoModulo;
    private String Informante;
    private String CodigoGradoMagnitud;
    private String CodigoUsuario;
    private String ReferenciaEmergencia;
    private String DescripcionEmergencia;
    private String Observacion;
    private String ApoyoInstitucion;
    private String CodigoBomberoMando;
    private String CodigoEstado;
    private String EstadoRegistro;
    private String Color;
    private String ColorHexa;
    private float Latitud;
    private float Longitud;
    private List<Vehicle> Vehiculos;
    private int Fotos;

    public Emergencie(String numeroParte, String fechaParte, String codigoTipoEmergencia, String tipoEmergencia, String tipoEmergenciaCompleto, String direccion, String numeroDireccion, String distrito, String ubigeo, String numeroTelefono, String codigoModulo, String informante, String codigoGradoMagnitud, String codigoUsuario, String referenciaEmergencia, String descripcionEmergencia, String observacion, String apoyoInstitucion, String codigoBomberoMando, String codigoEstado, String estadoRegistro, String color, String colorHexa, float latitud, float longitud, List<Vehicle> vehiculos, int fotos) {
        NumeroParte = numeroParte;
        FechaParte = fechaParte;
        CodigoTipoEmergencia = codigoTipoEmergencia;
        TipoEmergencia = tipoEmergencia;
        TipoEmergenciaCompleto = tipoEmergenciaCompleto;
        Direccion = direccion;
        NumeroDireccion = numeroDireccion;
        Distrito = distrito;
        Ubigeo = ubigeo;
        NumeroTelefono = numeroTelefono;
        CodigoModulo = codigoModulo;
        Informante = informante;
        CodigoGradoMagnitud = codigoGradoMagnitud;
        CodigoUsuario = codigoUsuario;
        ReferenciaEmergencia = referenciaEmergencia;
        DescripcionEmergencia = descripcionEmergencia;
        Observacion = observacion;
        ApoyoInstitucion = apoyoInstitucion;
        CodigoBomberoMando = codigoBomberoMando;
        CodigoEstado = codigoEstado;
        EstadoRegistro = estadoRegistro;
        Color = color;
        ColorHexa = colorHexa;
        Latitud = latitud;
        Longitud = longitud;
        Vehiculos = vehiculos;
        Fotos = fotos;
    }

    public String getNumeroParte() {
        return NumeroParte;
    }

    public void setNumeroParte(String numeroParte) {
        NumeroParte = numeroParte;
    }

    public String getFechaParte() {
        return FechaParte;
    }

    public void setFechaParte(String fechaParte) {
        FechaParte = fechaParte;
    }

    public String getCodigoTipoEmergencia() {
        return CodigoTipoEmergencia;
    }

    public void setCodigoTipoEmergencia(String codigoTipoEmergencia) {
        CodigoTipoEmergencia = codigoTipoEmergencia;
    }

    public String getTipoEmergencia() {
        return TipoEmergencia;
    }

    public void setTipoEmergencia(String tipoEmergencia) {
        TipoEmergencia = tipoEmergencia;
    }

    public String getTipoEmergenciaCompleto() {
        return TipoEmergenciaCompleto;
    }

    public void setTipoEmergenciaCompleto(String tipoEmergenciaCompleto) {
        TipoEmergenciaCompleto = tipoEmergenciaCompleto;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String direccion) {
        Direccion = direccion;
    }

    public String getNumeroDireccion() {
        return NumeroDireccion;
    }

    public void setNumeroDireccion(String numeroDireccion) {
        NumeroDireccion = numeroDireccion;
    }

    public String getDistrito() {
        return Distrito;
    }

    public void setDistrito(String distrito) {
        Distrito = distrito;
    }

    public String getUbigeo() {
        return Ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        Ubigeo = ubigeo;
    }

    public String getNumeroTelefono() {
        return NumeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono) {
        NumeroTelefono = numeroTelefono;
    }

    public String getCodigoModulo() {
        return CodigoModulo;
    }

    public void setCodigoModulo(String codigoModulo) {
        CodigoModulo = codigoModulo;
    }

    public String getInformante() {
        return Informante;
    }

    public void setInformante(String informante) {
        Informante = informante;
    }

    public String getCodigoGradoMagnitud() {
        return CodigoGradoMagnitud;
    }

    public void setCodigoGradoMagnitud(String codigoGradoMagnitud) {
        CodigoGradoMagnitud = codigoGradoMagnitud;
    }

    public String getCodigoUsuario() {
        return CodigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        CodigoUsuario = codigoUsuario;
    }

    public String getReferenciaEmergencia() {
        return ReferenciaEmergencia;
    }

    public void setReferenciaEmergencia(String referenciaEmergencia) {
        ReferenciaEmergencia = referenciaEmergencia;
    }

    public String getDescripcionEmergencia() {
        return DescripcionEmergencia;
    }

    public void setDescripcionEmergencia(String descripcionEmergencia) {
        DescripcionEmergencia = descripcionEmergencia;
    }

    public String getObservacion() {
        return Observacion;
    }

    public void setObservacion(String observacion) {
        Observacion = observacion;
    }

    public String getApoyoInstitucion() {
        return ApoyoInstitucion;
    }

    public void setApoyoInstitucion(String apoyoInstitucion) {
        ApoyoInstitucion = apoyoInstitucion;
    }

    public String getCodigoBomberoMando() {
        return CodigoBomberoMando;
    }

    public void setCodigoBomberoMando(String codigoBomberoMando) {
        CodigoBomberoMando = codigoBomberoMando;
    }

    public String getCodigoEstado() {
        return CodigoEstado;
    }

    public void setCodigoEstado(String codigoEstado) {
        CodigoEstado = codigoEstado;
    }

    public String getEstadoRegistro() {
        return EstadoRegistro;
    }

    public void setEstadoRegistro(String estadoRegistro) {
        EstadoRegistro = estadoRegistro;
    }

    public String getColor() {
        return Color;
    }

    public void setColor(String color) {
        Color = color;
    }

    public String getColorHexa() {
        return ColorHexa;
    }

    public void setColorHexa(String colorHexa) {
        ColorHexa = colorHexa;
    }

    public float getLatitud() {
        return Latitud;
    }

    public void setLatitud(float latitud) {
        Latitud = latitud;
    }

    public float getLongitud() {
        return Longitud;
    }

    public void setLongitud(float longitud) {
        Longitud = longitud;
    }

    public List<Vehicle> getVehiculos() {
        return Vehiculos;
    }

    public void setVehiculos(List<Vehicle> vehiculos) {
        Vehiculos = vehiculos;
    }

    public int getFotos() {
        return Fotos;
    }

    public void setFotos(int fotos) {
        Fotos = fotos;
    }
}
