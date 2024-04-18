package com.example.backendstudormy.domain.service;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class CalculateOceanTraits {

    private Map<String, Double> openness_traits = new HashMap<>();
    private Map<String, Double> conscientiousness_traits = new HashMap<>();
    private Map<String, Double> extroversion_traits = new HashMap<>();
    private Map<String, Double> agreeableness_traits = new HashMap<>();
    private Map<String, Double> neuroticism_traits = new HashMap<>();
    public CalculateOceanTraits (List<Integer>openness_values,List<Integer>conscientiousness_values,List<Integer>extroversion_values,List<Integer>agreableness_values,List<Integer>neuroticism_values) {

        constructDictionary(oceanKeys("OPN"),openness_values,openness_traits);
        constructDictionary(oceanKeys("CSN"),conscientiousness_values,conscientiousness_traits);
        constructDictionary(oceanKeys("EXT"),extroversion_values,extroversion_traits);
        constructDictionary(oceanKeys("AGR"),agreableness_values,agreeableness_traits);
        constructDictionary(oceanKeys("EST"),neuroticism_values,neuroticism_traits);
    }
    private void constructDictionary(List<String> ocean_keys,List<Integer>ocean_values, Map<String, Double> ocean_traits ) {
        if (ocean_keys.size() == ocean_values.size()) {
            for (int index = 0; index < ocean_keys.size(); index++) {
                Double valueToDouble  = Double.valueOf(ocean_values.get(index));
                ocean_traits.put(ocean_keys.get(index), valueToDouble );
            }
        }
    }
    private List<String> oceanKeys (String ocean_name) {
        List<String> keys = new ArrayList<>();
        for (int index = 1 ; index <= 10; index ++)
        {
            keys.add(ocean_name + index);
        }
        return keys;

    }
    public List<String> invertedColumnNames()
    {
        return List.of("EXT","EXT4","EXT6","EXT8","EXT10",
                "EST2","EST4",
                "AGR1","AGR3","AGR5","AGR7",
                "CSN2","CSN4","CSN6","CSN8",
                "OPN2","OPN4","OPN6");
    }
    private List<String> getTraitsWithoutTheInverted(String oceanTraitName)
    {
        List<String> traitsWithoutInverted = oceanKeys(oceanTraitName);
        traitsWithoutInverted.removeAll(invertedColumnNames());
        return  traitsWithoutInverted;
    }

    private double normalizeValue(double value, int minimValue, int maximumValue)
    {
        double colMin = 1.0;
        double colMax = 5.0;
        return  minimValue + ((value - colMin) * (maximumValue - minimValue) / (colMax - colMin));
    }

    public  double invertScale(double value, int minimValue, int maximumValue) {
        double colMin = 1.0;
        double colMax = 5.0;
        double colMid = (colMax + colMin) / 2.0;

        value = colMid - (value - colMid);
        return  minimValue + ((value - colMin) * (maximumValue - minimValue) / (colMax - colMin));

    }

    private void scaleTraitsForOne(Map<String,Double> dictionary , String traitName){
        List<String> invertedColumnNames = invertedColumnNames();
        double newValue;
        for (Map.Entry<String, Double> entry : dictionary.entrySet()) {

            String key = entry.getKey();
            double value = entry.getValue();
            if (invertedColumnNames.contains(key)) {
                newValue = invertScale(value, -1, 1);
            }
            else {
                newValue = normalizeValue(value, -1, 1);
            }
            dictionary.put(key, newValue);
        }
    }
    private void scaleTraitsForAll()
    {
        scaleTraitsForOne(this.openness_traits,"OPN");
        scaleTraitsForOne(this.conscientiousness_traits,"CSN");
        scaleTraitsForOne(this.extroversion_traits,"EXT");
        scaleTraitsForOne(this.agreeableness_traits,"AGR");
        scaleTraitsForOne(this.neuroticism_traits,"EST");
    }
    private List<Double> sumTraits(){
        double open_score = this.openness_traits.values().stream().mapToDouble(Double::doubleValue).sum();
        double conscientiousness_score = this.conscientiousness_traits.values().stream().mapToDouble(Double::doubleValue).sum();
        double extroversion_score = this.extroversion_traits.values().stream().mapToDouble(Double::doubleValue).sum();
        double agreeableness_score = this.agreeableness_traits.values().stream().mapToDouble(Double::doubleValue).sum();
        double neuroticism_score = this.neuroticism_traits.values().stream().mapToDouble(Double::doubleValue).sum();

        return List.of(open_score,conscientiousness_score,extroversion_score,agreeableness_score,neuroticism_score);
    }
    public List<Double> sumTraitsAndScale(){
        scaleTraitsForAll();
        return sumTraits();
    }

}
