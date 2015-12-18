package com.vmaudgalya.inbornerrorsofmetabolism;

import java.util.ArrayList;

/**
 * Created by vmaudgalya on 12/17/15.
 */
public class Utility {
  private String[] symptoms;
  private String[] labResults;
  private String[] diseases;

  public Utility() {
    symptoms = new String[]{"Diarrhea", "Exercise intolerance", "Familial myocardial infarct/stroke",
    "Muscle cramps/spasticity", "Peripheral neuropathy", "Recurrent emesis", "Symptoms of pancreatitis", "Upward gaze paralysis"}; // Read from text files later
    labResults = new String[]{"Abnormal liver function tests (e.g., elevated transaminase or hyperbilirubinemia levels)",
            "Hypoglycemia", "Hypophosphatemia", "Hypouricemia", "Increased CSF protein", "Ketosis", "Metabolic acidosis"};
    diseases = new String[]{"Galactosemia", "Maple sugar urine disease", "Phenylketonuria", "Glycogen storage disease, type Ia (von Gierke's disease)",
            "Medium-chain acyl-CoA dehydrogenase deficiency", "Pyruvate dehydrogenase deficiency", "Gaucher's disease",
    "Fabry's disease", "Hurler's syndrome", "Methylmalonicaciduria", "Propionic aciduria", "Zellweger syndrome", "Ornithine transcarbamylase deficiency"};
  }

  public String[] getSymptoms() {
    return this.symptoms;
  }

  public String[] getLabResults() {
    return this.labResults;
  }

  public String[] getAllDiseases() {
    return this.diseases;
  }

  public ArrayList<String> filterBySymptoms(ArrayList<String> diseases, ArrayList<String> symptoms) {
    // Rules for filtering by symptoms here...
    return diseases;
  }

  public ArrayList<String> filterByLabResults(ArrayList<String> diseases, ArrayList<String> labResults) {
    // Rules for filtering by lab results here
    return diseases;
  }
}
