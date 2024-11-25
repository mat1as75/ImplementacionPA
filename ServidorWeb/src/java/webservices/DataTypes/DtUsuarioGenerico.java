
package webservices.DataTypes;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para dtUsuarioGenerico complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dtUsuarioGenerico"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="activo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="apellidoUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="biografia" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="contidadSeguidores" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="contrasenaUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="dirSitioWeb" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="fecNac" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="fechaBaja" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="fotoPerfil" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nicknameUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nicknamesSeguidores" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="nicknamesSeguidos" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="nombreUsuario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="nombresAlbumesFavoritos"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="nombresAlbumesPublicados"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="nombresListasRCreadas" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="nombresListasRCreadasPublicas" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="nombresListasRFavoritas" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="nombresTemasFavoritos"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;complexContent&gt;
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                           &lt;sequence&gt;
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                           &lt;/sequence&gt;
 *                         &lt;/restriction&gt;
 *                       &lt;/complexContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="suscripcion" type="{http://webservices.espotify/}dtSuscripcion" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtUsuarioGenerico", propOrder = {
    "activo",
    "apellidoUsuario",
    "biografia",
    "contidadSeguidores",
    "contrasenaUsuario",
    "dirSitioWeb",
    "email",
    "fecNac",
    "fechaBaja",
    "fotoPerfil",
    "nicknameUsuario",
    "nicknamesSeguidores",
    "nicknamesSeguidos",
    "nombreUsuario",
    "nombresAlbumesFavoritos",
    "nombresAlbumesPublicados",
    "nombresListasRCreadas",
    "nombresListasRCreadasPublicas",
    "nombresListasRFavoritas",
    "nombresTemasFavoritos",
    "suscripcion"
})
public class DtUsuarioGenerico {

    protected Boolean activo;
    protected String apellidoUsuario;
    protected String biografia;
    protected int contidadSeguidores;
    protected String contrasenaUsuario;
    protected String dirSitioWeb;
    protected String email;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecNac;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaBaja;
    protected String fotoPerfil;
    protected String nicknameUsuario;
    @XmlElement(nillable = true)
    protected List<String> nicknamesSeguidores;
    @XmlElement(nillable = true)
    protected List<String> nicknamesSeguidos;
    protected String nombreUsuario;
    @XmlElement(required = true)
    protected DtUsuarioGenerico.NombresAlbumesFavoritos nombresAlbumesFavoritos;
    @XmlElement(required = true)
    protected DtUsuarioGenerico.NombresAlbumesPublicados nombresAlbumesPublicados;
    @XmlElement(nillable = true)
    protected List<String> nombresListasRCreadas;
    @XmlElement(nillable = true)
    protected List<String> nombresListasRCreadasPublicas;
    @XmlElement(nillable = true)
    protected List<String> nombresListasRFavoritas;
    @XmlElement(required = true)
    protected DtUsuarioGenerico.NombresTemasFavoritos nombresTemasFavoritos;
    protected DtSuscripcion suscripcion;

    /**
     * Obtiene el valor de la propiedad activo.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isActivo() {
        return activo;
    }

    /**
     * Define el valor de la propiedad activo.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setActivo(Boolean value) {
        this.activo = value;
    }

    /**
     * Obtiene el valor de la propiedad apellidoUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    /**
     * Define el valor de la propiedad apellidoUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellidoUsuario(String value) {
        this.apellidoUsuario = value;
    }

    /**
     * Obtiene el valor de la propiedad biografia.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBiografia() {
        return biografia;
    }

    /**
     * Define el valor de la propiedad biografia.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBiografia(String value) {
        this.biografia = value;
    }

    /**
     * Obtiene el valor de la propiedad contidadSeguidores.
     * 
     */
    public int getContidadSeguidores() {
        return contidadSeguidores;
    }

    /**
     * Define el valor de la propiedad contidadSeguidores.
     * 
     */
    public void setContidadSeguidores(int value) {
        this.contidadSeguidores = value;
    }

    /**
     * Obtiene el valor de la propiedad contrasenaUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrasenaUsuario() {
        return contrasenaUsuario;
    }

    /**
     * Define el valor de la propiedad contrasenaUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrasenaUsuario(String value) {
        this.contrasenaUsuario = value;
    }

    /**
     * Obtiene el valor de la propiedad dirSitioWeb.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirSitioWeb() {
        return dirSitioWeb;
    }

    /**
     * Define el valor de la propiedad dirSitioWeb.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirSitioWeb(String value) {
        this.dirSitioWeb = value;
    }

    /**
     * Obtiene el valor de la propiedad email.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Define el valor de la propiedad email.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Obtiene el valor de la propiedad fecNac.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecNac() {
        return fecNac;
    }

    /**
     * Define el valor de la propiedad fecNac.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecNac(XMLGregorianCalendar value) {
        this.fecNac = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaBaja.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaBaja() {
        return fechaBaja;
    }

    /**
     * Define el valor de la propiedad fechaBaja.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaBaja(XMLGregorianCalendar value) {
        this.fechaBaja = value;
    }

    /**
     * Obtiene el valor de la propiedad fotoPerfil.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFotoPerfil() {
        return fotoPerfil;
    }

    /**
     * Define el valor de la propiedad fotoPerfil.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFotoPerfil(String value) {
        this.fotoPerfil = value;
    }

    /**
     * Obtiene el valor de la propiedad nicknameUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNicknameUsuario() {
        return nicknameUsuario;
    }

    /**
     * Define el valor de la propiedad nicknameUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNicknameUsuario(String value) {
        this.nicknameUsuario = value;
    }

    /**
     * Gets the value of the nicknamesSeguidores property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nicknamesSeguidores property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNicknamesSeguidores().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNicknamesSeguidores() {
        if (nicknamesSeguidores == null) {
            nicknamesSeguidores = new ArrayList<String>();
        }
        return this.nicknamesSeguidores;
    }

    /**
     * Gets the value of the nicknamesSeguidos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nicknamesSeguidos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNicknamesSeguidos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNicknamesSeguidos() {
        if (nicknamesSeguidos == null) {
            nicknamesSeguidos = new ArrayList<String>();
        }
        return this.nicknamesSeguidos;
    }

    /**
     * Obtiene el valor de la propiedad nombreUsuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Define el valor de la propiedad nombreUsuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreUsuario(String value) {
        this.nombreUsuario = value;
    }

    /**
     * Obtiene el valor de la propiedad nombresAlbumesFavoritos.
     * 
     * @return
     *     possible object is
     *     {@link DtUsuarioGenerico.NombresAlbumesFavoritos }
     *     
     */
    public DtUsuarioGenerico.NombresAlbumesFavoritos getNombresAlbumesFavoritos() {
        return nombresAlbumesFavoritos;
    }

    /**
     * Define el valor de la propiedad nombresAlbumesFavoritos.
     * 
     * @param value
     *     allowed object is
     *     {@link DtUsuarioGenerico.NombresAlbumesFavoritos }
     *     
     */
    public void setNombresAlbumesFavoritos(DtUsuarioGenerico.NombresAlbumesFavoritos value) {
        this.nombresAlbumesFavoritos = value;
    }

    /**
     * Obtiene el valor de la propiedad nombresAlbumesPublicados.
     * 
     * @return
     *     possible object is
     *     {@link DtUsuarioGenerico.NombresAlbumesPublicados }
     *     
     */
    public DtUsuarioGenerico.NombresAlbumesPublicados getNombresAlbumesPublicados() {
        return nombresAlbumesPublicados;
    }

    /**
     * Define el valor de la propiedad nombresAlbumesPublicados.
     * 
     * @param value
     *     allowed object is
     *     {@link DtUsuarioGenerico.NombresAlbumesPublicados }
     *     
     */
    public void setNombresAlbumesPublicados(DtUsuarioGenerico.NombresAlbumesPublicados value) {
        this.nombresAlbumesPublicados = value;
    }

    /**
     * Gets the value of the nombresListasRCreadas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nombresListasRCreadas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNombresListasRCreadas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNombresListasRCreadas() {
        if (nombresListasRCreadas == null) {
            nombresListasRCreadas = new ArrayList<String>();
        }
        return this.nombresListasRCreadas;
    }

    /**
     * Gets the value of the nombresListasRCreadasPublicas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nombresListasRCreadasPublicas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNombresListasRCreadasPublicas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNombresListasRCreadasPublicas() {
        if (nombresListasRCreadasPublicas == null) {
            nombresListasRCreadasPublicas = new ArrayList<String>();
        }
        return this.nombresListasRCreadasPublicas;
    }

    /**
     * Gets the value of the nombresListasRFavoritas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nombresListasRFavoritas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNombresListasRFavoritas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getNombresListasRFavoritas() {
        if (nombresListasRFavoritas == null) {
            nombresListasRFavoritas = new ArrayList<String>();
        }
        return this.nombresListasRFavoritas;
    }

    /**
     * Obtiene el valor de la propiedad nombresTemasFavoritos.
     * 
     * @return
     *     possible object is
     *     {@link DtUsuarioGenerico.NombresTemasFavoritos }
     *     
     */
    public DtUsuarioGenerico.NombresTemasFavoritos getNombresTemasFavoritos() {
        return nombresTemasFavoritos;
    }

    /**
     * Define el valor de la propiedad nombresTemasFavoritos.
     * 
     * @param value
     *     allowed object is
     *     {@link DtUsuarioGenerico.NombresTemasFavoritos }
     *     
     */
    public void setNombresTemasFavoritos(DtUsuarioGenerico.NombresTemasFavoritos value) {
        this.nombresTemasFavoritos = value;
    }

    /**
     * Obtiene el valor de la propiedad suscripcion.
     * 
     * @return
     *     possible object is
     *     {@link DtSuscripcion }
     *     
     */
    public DtSuscripcion getSuscripcion() {
        return suscripcion;
    }

    /**
     * Define el valor de la propiedad suscripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link DtSuscripcion }
     *     
     */
    public void setSuscripcion(DtSuscripcion value) {
        this.suscripcion = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
     *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class NombresAlbumesFavoritos {

        protected List<DtUsuarioGenerico.NombresAlbumesFavoritos.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DtUsuarioGenerico.NombresAlbumesFavoritos.Entry }
         * 
         * 
         */
        public List<DtUsuarioGenerico.NombresAlbumesFavoritos.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<DtUsuarioGenerico.NombresAlbumesFavoritos.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
         *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected Long key;
            protected String value;

            /**
             * Obtiene el valor de la propiedad key.
             * 
             * @return
             *     possible object is
             *     {@link Long }
             *     
             */
            public Long getKey() {
                return key;
            }

            /**
             * Define el valor de la propiedad key.
             * 
             * @param value
             *     allowed object is
             *     {@link Long }
             *     
             */
            public void setKey(Long value) {
                this.key = value;
            }

            /**
             * Obtiene el valor de la propiedad value.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Define el valor de la propiedad value.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
     *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class NombresAlbumesPublicados {

        protected List<DtUsuarioGenerico.NombresAlbumesPublicados.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DtUsuarioGenerico.NombresAlbumesPublicados.Entry }
         * 
         * 
         */
        public List<DtUsuarioGenerico.NombresAlbumesPublicados.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<DtUsuarioGenerico.NombresAlbumesPublicados.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
         *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected Long key;
            protected String value;

            /**
             * Obtiene el valor de la propiedad key.
             * 
             * @return
             *     possible object is
             *     {@link Long }
             *     
             */
            public Long getKey() {
                return key;
            }

            /**
             * Define el valor de la propiedad key.
             * 
             * @param value
             *     allowed object is
             *     {@link Long }
             *     
             */
            public void setKey(Long value) {
                this.key = value;
            }

            /**
             * Obtiene el valor de la propiedad value.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Define el valor de la propiedad value.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

        }

    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;complexContent&gt;
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *                 &lt;sequence&gt;
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
     *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *                 &lt;/sequence&gt;
     *               &lt;/restriction&gt;
     *             &lt;/complexContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class NombresTemasFavoritos {

        protected List<DtUsuarioGenerico.NombresTemasFavoritos.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DtUsuarioGenerico.NombresTemasFavoritos.Entry }
         * 
         * 
         */
        public List<DtUsuarioGenerico.NombresTemasFavoritos.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<DtUsuarioGenerico.NombresTemasFavoritos.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;complexContent&gt;
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
         *       &lt;sequence&gt;
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
         *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
         *       &lt;/sequence&gt;
         *     &lt;/restriction&gt;
         *   &lt;/complexContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected Long key;
            protected String value;

            /**
             * Obtiene el valor de la propiedad key.
             * 
             * @return
             *     possible object is
             *     {@link Long }
             *     
             */
            public Long getKey() {
                return key;
            }

            /**
             * Define el valor de la propiedad key.
             * 
             * @param value
             *     allowed object is
             *     {@link Long }
             *     
             */
            public void setKey(Long value) {
                this.key = value;
            }

            /**
             * Obtiene el valor de la propiedad value.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * Define el valor de la propiedad value.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

        }

    }

}
