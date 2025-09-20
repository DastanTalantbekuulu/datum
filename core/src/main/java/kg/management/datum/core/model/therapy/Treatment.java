package kg.management.datum.core.model.therapy;

import kg.management.datum.core.constants.ToothNumber;

public record Treatment(
     long id,
     long templateId,
     long employeeId,
     long anamnesisId,
     ToothNumber tooth
){}
