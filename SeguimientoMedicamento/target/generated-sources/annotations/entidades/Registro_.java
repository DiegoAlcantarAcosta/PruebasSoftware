package entidades;

import entidades.Medicamento;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-02T20:53:09", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Registro.class)
public class Registro_ { 

    public static volatile SingularAttribute<Registro, Medicamento> IdMedicamento;
    public static volatile SingularAttribute<Registro, Medicamento> medicamento;
    public static volatile SingularAttribute<Registro, Long> id;
    public static volatile SingularAttribute<Registro, Date> horaConsumo;
    public static volatile SingularAttribute<Registro, Integer> cantidadConsumo;
    public static volatile SingularAttribute<Registro, Boolean> tomado;

}