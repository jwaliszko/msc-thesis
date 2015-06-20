package traffic_web.logic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import traffic_web.tools.StringKeyEntryComparator;
import traffic_web.tools.Utility;

import traffic.logic.ClassesNamesProvider;
import traffic.logic.OntologyFacade;

public class VariousQuestionsLoader {
	
	private List<Entry<String, Boolean>> lowFrictionDangers;	
	private List<Entry<String, Boolean>> roadConstructionDangers;
	private List<Entry<String, Boolean>> roadObstaclesDangers;
	private List<Entry<String, Boolean>> secondCategoryRoadDangers;
	private List<Entry<String, Boolean>> weatherDangers;
	
	private List<List<Entry<String, Boolean>>> dangersList;
	
	private String lowFrictionDangersTitle;	
	private String roadConstructionDangersTitle;
	private String roadObstaclesDangersTitle;
	private String secondCategoryRoadDangersTitle;
	private String weatherDangersTitle;
	
	public VariousQuestionsLoader()
	{			
		dangersList = new ArrayList<List<Entry<String,Boolean>>>();
	}
	
	public void load(OntologyFacade facade, String lang)
	{				
		initializeDangers(facade, lang);
	}

	public List<Entry<String, Boolean>> getLowFrictionDangers() {
		return lowFrictionDangers;
	}

	public List<Entry<String, Boolean>> getRoadConstructionDangers() {
		return roadConstructionDangers;
	}

	public List<Entry<String, Boolean>> getRoadObstaclesDangers() {
		return roadObstaclesDangers;
	}

	public List<Entry<String, Boolean>> getSecondCategoryRoadDangers() {
		return secondCategoryRoadDangers;
	}

	public List<Entry<String, Boolean>> getWeatherDangers() {
		return weatherDangers;
	}

	public String getLowFrictionDangersTitle() {
		return lowFrictionDangersTitle;
	}

	public String getRoadConstructionDangersTitle() {
		return roadConstructionDangersTitle;
	}

	public String getRoadObstaclesDangersTitle() {
		return roadObstaclesDangersTitle;
	}

	public String getSecondCategoryRoadDangersTitle() {
		return secondCategoryRoadDangersTitle;
	}

	public String getWeatherDangersTitle() {
		return weatherDangersTitle;
	}
	
	private void initializeDangers(OntologyFacade facade, String lang) {
		
		this.lowFrictionDangersTitle = Utility.replaceCharsIfPossible(facade.getClassDescription(ClassesNamesProvider.LOW_FRICTION_DANGER, lang), '.', ':');		
		this.roadConstructionDangersTitle = Utility.replaceCharsIfPossible(facade.getClassDescription(ClassesNamesProvider.ROAD_CONSTRUCTION_DANGER, lang), '.', ':');
		this.roadObstaclesDangersTitle = Utility.replaceCharsIfPossible(facade.getClassDescription(ClassesNamesProvider.ROAD_OBSTACLES_DANGER, lang), '.', ':');
		this.secondCategoryRoadDangersTitle = Utility.replaceCharsIfPossible(facade.getClassDescription(ClassesNamesProvider.SECOND_CATEGORY_ROAD_DANGER, lang), '.', ':');
		this.weatherDangersTitle = Utility.replaceCharsIfPossible(facade.getClassDescription(ClassesNamesProvider.WEATHER_DANGER, lang), '.', ':');
		
		this.lowFrictionDangers = facade.getSubclassesDescriptionsWithAggregationFlag(ClassesNamesProvider.LOW_FRICTION_DANGER, lang);
		this.roadConstructionDangers = facade.getSubclassesDescriptionsWithAggregationFlag(ClassesNamesProvider.ROAD_CONSTRUCTION_DANGER, lang);
		this.roadObstaclesDangers = facade.getSubclassesDescriptionsWithAggregationFlag(ClassesNamesProvider.ROAD_OBSTACLES_DANGER, lang);
		this.secondCategoryRoadDangers = facade.getSubclassesDescriptionsWithAggregationFlag(ClassesNamesProvider.SECOND_CATEGORY_ROAD_DANGER, lang);
		this.weatherDangers = facade.getSubclassesDescriptionsWithAggregationFlag(ClassesNamesProvider.WEATHER_DANGER, lang);			
						
		sortCollections();
	}
	
	private void sortCollections()
	{		
		StringKeyEntryComparator comparator = new StringKeyEntryComparator();
				
		dangersList.add(this.lowFrictionDangers);
		dangersList.add(this.roadConstructionDangers);
		dangersList.add(this.roadObstaclesDangers);
		dangersList.add(this.secondCategoryRoadDangers);
		dangersList.add(this.weatherDangers);
		
		for(List<Entry<String, Boolean>> current : this.dangersList)
		{
			if(current != null)
			{
				Collections.sort(current, comparator);
			}
		}
	}
}
