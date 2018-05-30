package com.tmajor.lib.log.pattern;

import com.tmajor.lib.log.model.LogModel;
import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class LogMessageExtractorTest {

    private static final UUID uuid = UUID.randomUUID();
    private static final LogModel model = new LogModel.LogModelBuilder()
            .setMessage("Test")
            .setTechnicalId("111")
            .setBusinessId("B1")
            .setApp("TT")
            .setUuid(uuid.toString())
            .setParams(null)
            .buid();

    //_uuid: "a92b686f-194d-4336-9077-fcb80d1687b9" _app: "TEST" _tid: "111" _bid: "be3" _msg: "Test message"

    @Test
    public void extractUuid() {

        String extractedUuid = LogMessageExtractor.extractUuid(model.getLogMessage());
        Assert.assertNotNull(extractedUuid);
        Assert.assertTrue(uuid.toString().equalsIgnoreCase(extractedUuid));
        extractedUuid = LogMessageExtractor.extractUuid("");
        Assert.assertNotNull(extractedUuid);
        Assert.assertTrue(extractedUuid.isEmpty());
        String missingUuid = "_app: \"TT\" _tid: \"111\" _bid: \"B1\" _msg: \"Test\" ";
        extractedUuid = LogMessageExtractor.extractUuid(missingUuid);
        Assert.assertNotNull(extractedUuid);
        Assert.assertTrue(extractedUuid.isEmpty());
    }

    @Test
    public void extractApp() {
        String extractedApp = LogMessageExtractor.extractApp(model.getLogMessage());
        Assert.assertNotNull(extractedApp);
        Assert.assertTrue(model.getApp().equalsIgnoreCase(extractedApp));
        extractedApp = LogMessageExtractor.extractApp("");
        Assert.assertNotNull(extractedApp);
        Assert.assertTrue(extractedApp.isEmpty());
        String missingApp = "_uuid: \"a92b686f-194d-4336-9077-fcb80d1687b9\" _tid: \"111\" _bid: \"be3\" _msg: \"Test message\"";
        extractedApp = LogMessageExtractor.extractApp(missingApp);
        Assert.assertNotNull(extractedApp);
        Assert.assertTrue(extractedApp.isEmpty());
    }

    @Test
    public void extractTechnicalId() {
        String extractTechnicalId = LogMessageExtractor.extractTechnicalId(model.getLogMessage());
        Assert.assertNotNull(extractTechnicalId);
        Assert.assertTrue(model.getTechnicalId().equalsIgnoreCase(extractTechnicalId));
        extractTechnicalId = LogMessageExtractor.extractTechnicalId("");
        Assert.assertNotNull(extractTechnicalId);
        Assert.assertTrue(extractTechnicalId.isEmpty());
        String missingTechnicalId = "_uuid: \"a92b686f-194d-4336-9077-fcb80d1687b9\" _app: \"TEST\" _bid: \"be3\" _msg: \"Test message\"";
        extractTechnicalId = LogMessageExtractor.extractTechnicalId(missingTechnicalId);
        Assert.assertNotNull(extractTechnicalId);
        Assert.assertTrue(extractTechnicalId.isEmpty());
    }

    @Test
    public void extractBusinessId() {
        String extractBusinessId = LogMessageExtractor.extractBusinessId(model.getLogMessage());
        Assert.assertNotNull(extractBusinessId);
        Assert.assertTrue(model.getBusinessId().equalsIgnoreCase(extractBusinessId));
        extractBusinessId = LogMessageExtractor.extractBusinessId("");
        Assert.assertNotNull(extractBusinessId);
        Assert.assertTrue(extractBusinessId.isEmpty());
        String missingBusinessId = "_uuid: \"a92b686f-194d-4336-9077-fcb80d1687b9\" _app: \"TEST\" _tid: \"111\" _msg: \"Test message\"";
        extractBusinessId = LogMessageExtractor.extractBusinessId(missingBusinessId);
        Assert.assertNotNull(extractBusinessId);
        Assert.assertTrue(extractBusinessId.isEmpty());
    }

    @Test
    public void extractMessage() {
        String extractMessage = LogMessageExtractor.extractMessage(model.getLogMessage());
        Assert.assertNotNull(extractMessage);
        Assert.assertTrue(model.getMessage().equalsIgnoreCase(extractMessage));
        extractMessage = LogMessageExtractor.extractMessage("");
        Assert.assertNotNull(extractMessage);
        Assert.assertTrue(extractMessage.isEmpty());
        String missingMessage = "_uuid: \"a92b686f-194d-4336-9077-fcb80d1687b9\" _app: \"TEST\" _tid: \"111\" _bid: \"be3\"";
        extractMessage = LogMessageExtractor.extractMessage(missingMessage);
        Assert.assertNotNull(extractMessage);
        Assert.assertTrue(extractMessage.isEmpty());
    }

    @Test
    public void extractModel() {
        LogModel logModel = LogMessageExtractor.extractModel(model.getLogMessage());
        Assert.assertEquals(logModel, model);
    }
}