package ru.liga.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import ru.liga.model.Rule;
import ru.liga.service.BuiltInFunctions;
import ru.liga.service.ResponseUpdaterService;
import ru.liga.utils.DomXmlParser;
import ru.liga.utils.XpathQueryAnalyzer;

import java.util.Arrays;
import java.util.function.Consumer;

@Component
public class ResponseUpdaterServiceImplementation implements ResponseUpdaterService {
    private final RuleGetterServiceImplementation ruleGetterServiceImplementation;
    private final XpathQueryAnalyzer xpathQueryAnalyzer;
    private final DomXmlParser domXmlParser;
    private final BuiltInFunctions builtInFunctions;
    private String responseTemplate;
    private Document document;

    @Autowired
    public ResponseUpdaterServiceImplementation(RuleGetterServiceImplementation ruleGetterServiceImplementation, XpathQueryAnalyzer xpathQueryAnalyzer, DomXmlParser domXmlParser, BuiltInFunctions builtInFunctions) {
        this.ruleGetterServiceImplementation = ruleGetterServiceImplementation;
        this.xpathQueryAnalyzer = xpathQueryAnalyzer;
        this.domXmlParser = domXmlParser;
        this.builtInFunctions = builtInFunctions;
    }

    @Override
    public String getUpdatedResponse(String request) {
        Rule matchingRule = ruleGetterServiceImplementation.getRule(request);
        this.responseTemplate = matchingRule.getXML_RESPONSE();
        this.document = domXmlParser.getXmlDocument(responseTemplate);
        updateResponseTemplate(this::updateWithEachMap, matchingRule.getXPATH_MAP());
        updateResponseTemplate(this::updateWithEachBuiltInFunction, matchingRule.getAPPLY_FUNCTION());
        return domXmlParser.getStringFromDocument(document);
    }

    private void updateResponseTemplate(Consumer<String> updaterLogic, String map) {
        Arrays.stream(map.split(";"))
                .forEach(updaterLogic);
    }

    private void updateWithEachBuiltInFunction(String map) {
        String[] mapArray = map.split(":");
        Node node = xpathQueryAnalyzer.getQueryNodeResult(mapArray[0], document);
        String resultFromFunction = builtInFunctions.getStringFromFunction(mapArray[1]);
        if (resultFromFunction != null)
            node.setTextContent(resultFromFunction);
    }

    private void updateWithEachMap(String map) {
        String[] mapArray = map.split(":");
        Node node = xpathQueryAnalyzer.getQueryNodeResult(mapArray[0], document);
        node.setTextContent(xpathQueryAnalyzer.getQueryStringResult(mapArray[1], ruleGetterServiceImplementation.getDocument()));
    }

}
