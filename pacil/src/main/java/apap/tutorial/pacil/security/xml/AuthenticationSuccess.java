package apap.tutorial.pacil.security.xml;

import lombok.Getter;
import lombok.Setter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;

@Setter
@Getter
@XmlAccessorType(XmlAccessType.FIELD)
public class AuthenticationSuccess {

    @XmlElement(name = "user", namespace = "http://www.yale.edu/tp/cas")
    private String user;

    @XmlElement(name = "attributes", namespace = "http://www.yale.edu/tp/cas")
    private Attributes attributes;
}