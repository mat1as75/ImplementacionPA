package webservices.DataTypes;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dtParticulares complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dtParticulares"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://webservices.espotify/}dtListaReproduccion"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="micliente" type="{http://webservices.espotify/}dtCliente" minOccurs="0"/&gt;
 *         &lt;element name="soyrivada" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtParticulares", propOrder = {
    "micliente",
    "soyrivada"
})
public class DtParticulares
    extends DtListaReproduccion
{

    protected DtCliente micliente;
    protected boolean soyrivada;

    /**
     * Gets the value of the micliente property.
     * 
     * @return
     *     possible object is
     *     {@link DtCliente }
     *     
     */
    public DtCliente getMicliente() {
        return micliente;
    }

    /**
     * Sets the value of the micliente property.
     * 
     * @param value
     *     allowed object is
     *     {@link DtCliente }
     *     
     */
    public void setMicliente(DtCliente value) {
        this.micliente = value;
    }

    /**
     * Gets the value of the soyrivada property.
     * 
     */
    public boolean isSoyrivada() {
        return soyrivada;
    }

    /**
     * Sets the value of the soyrivada property.
     * 
     */
    public void setSoyrivada(boolean value) {
        this.soyrivada = value;
    }

}
