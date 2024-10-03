package entidades;

import entidades.Registro;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2024-10-02T20:53:09", comments="EclipseLink-2.7.10.v20211216-rNA")
@StaticMetamodel(Medicamento.class)
public class Medicamento_ { 

    public static volatile SingularAttribute<Medicamento, String> tipoConsumo;
    public static volatile ListAttribute<Medicamento, Registro> lista;
    public static volatile SingularAttribute<Medicamento, Double> frecuencia;
    public static volatile SingularAttribute<Medicamento, Double> horaPrimeraDosis;
    public static volatile SingularAttribute<Medicamento, Long> id;
    public static volatile SingularAttribute<Medicamento, Integer> cantidad;
    public static volatile SingularAttribute<Medicamento, String> nombre;

}