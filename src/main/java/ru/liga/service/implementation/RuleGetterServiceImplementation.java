package ru.liga.service.implementation;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import ru.liga.model.Rule;
import ru.liga.repository.H2RulesRepository;
import ru.liga.service.RuleGetterService;
import ru.liga.utils.DomXmlParser;
import ru.liga.utils.XpathQueryAnalyzer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Component
public class RuleGetterServiceImplementation implements RuleGetterService {
    private final H2RulesRepository repo;
    private final DomXmlParser domXmlParser;
    private final XpathQueryAnalyzer xpathQueryAnalyzer;
    private Document document;
    private List<Rule> listOfMatchingRules;

    public RuleGetterServiceImplementation(H2RulesRepository repo, DomXmlParser domXmlParser, XpathQueryAnalyzer xpathQueryAnalyzer) {
        this.repo = repo;
        this.domXmlParser = domXmlParser;
        this.xpathQueryAnalyzer = xpathQueryAnalyzer;
    }

    @Override
    public Rule getRule(String request) {
        this.document = domXmlParser.getXmlDocument(request);
        listOfMatchingRules = new ArrayList<>();
        repo.findAll().forEach(this::findAllMatchingRules);
        listOfMatchingRules.sort(Comparator.comparing(Rule::getXPATH_QUERY_RULE_WEIGHT).reversed());
        if (!listOfMatchingRules.isEmpty())
            return listOfMatchingRules.get(0);
        return null;
    }

    private void findAllMatchingRules(Rule rule) {
        if (Boolean.parseBoolean(xpathQueryAnalyzer.getQueryStringResult(rule.getXPATH_QUERY(), this.document)))
            listOfMatchingRules.add(rule);
    }

    public Document getDocument() {
        return document;
    }
}
