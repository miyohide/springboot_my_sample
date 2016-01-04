package mysample.webapp.basic.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Country {

    @Id
    @Column(nullable = false, length=3)
    private String code;

    @Column(nullable = false, length=52)
    private String name;

    @Column(nullable = false, length=20)
    private String continent;

    @Column(nullable = false, length=26)
    private String region;

    @Column(nullable = false, precision = 8, scale = 2)
    private BigDecimal surfaceArea;

    private Long indepYear;

    @Column(nullable = false)
    private Long population;

    @Column(precision = 2, scale = 1)
    private BigDecimal lifeExpectancy;

    @Column(precision = 8, scale = 2)
    private BigDecimal gNP;

    @Column(precision = 8, scale = 2)
    private BigDecimal gNPOld;

    @Column(nullable = false, length=45)
    private String localName;

    @Column(nullable = false, length=45)
    private String governmentForm;

    @Column(nullable = false, length=60)
    private String headOfState;

    private Long capital;

    @Column(nullable = false, length=2)
    private String code2;
}
