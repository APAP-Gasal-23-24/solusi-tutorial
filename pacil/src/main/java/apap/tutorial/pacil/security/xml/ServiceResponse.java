package apap.tutorial.pacil.security.xml;

import lombok.Getter;
import lombok.Setter;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@Setter
@Getter
@XmlRootElement(name = "serviceResponse", namespace = "http://www.yale.edu/tp/cas")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServiceResponse {

    @XmlElement(name = "authenticationFailure", namespace = "http://www.yale.edu/tp/cas")
    private String authenticationFailure;

    @XmlElement(name = "authenticationSuccess", namespace = "http://www.yale.edu/tp/cas")
    private AuthenticationSuccess authenticationSuccess;
}