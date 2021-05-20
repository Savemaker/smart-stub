package ru.liga.model;

import javax.persistence.*;

@Entity
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long RULE_ID;
    private String XPATH_QUERY;
    private Integer XPATH_QUERY_RULE_WEIGHT;
    private String XPATH_MAP;
    private String APPLY_FUNCTION;

    @Lob
    private String XML_RESPONSE;

    protected Rule() {
    }

    public Rule(String XPATH_QUERY, Integer XPATH_QUERY_RULE_WEIGHT, String xpath_map, String apply_function, String XML_RESPONSE) {
        this.XPATH_QUERY = XPATH_QUERY;
        this.XPATH_QUERY_RULE_WEIGHT = XPATH_QUERY_RULE_WEIGHT;
        this.XPATH_MAP = xpath_map;
        APPLY_FUNCTION = apply_function;
        this.XML_RESPONSE = XML_RESPONSE;
    }

    public Long getRULE_ID() {
        return RULE_ID;
    }

    public String getXPATH_QUERY() {
        return XPATH_QUERY;
    }

    public Integer getXPATH_QUERY_RULE_WEIGHT() {
        return XPATH_QUERY_RULE_WEIGHT;
    }

    public String getXML_RESPONSE() {
        return XML_RESPONSE;
    }

    public String getXPATH_MAP() { return XPATH_MAP; }

    public String getAPPLY_FUNCTION() { return APPLY_FUNCTION; }
}
