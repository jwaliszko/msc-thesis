INSERT INTO access ("id", "username", "password") VALUES (1, 'sa', 'yKtRiV2ooqPqBPMb1+MXr4hZYyc=');	--plain password: traffc
ALTER SEQUENCE access_id_seq RESTART WITH 2;

INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (1, NULL, 'TrafficCondition', NULL);

	INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (2, 1, 'AreaCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (3, 2, 'BuiltUpCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (4, 2, 'UnbuiltCondition', NULL);
		
	INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (5, 1, 'CongestionCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (6, 5, 'HighCongestionCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (7, 5, 'LowCongestionCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (8, 5, 'NormalCongestionCondition', NULL);
		
	INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (9, 1, 'RoadConstructionCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (10, 9, 'BuildingMaterialCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (11, 10, 'AsphaltCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (12, 10, 'ConcreteCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (13, 10, 'GravelCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (14, 9, 'CrosswalkCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (15, 14, 'AboveGroundCrosswalkCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (16, 14, 'GroundCrosswalkCondition', NULL);
				INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (17, 16, 'SignedCrosswalkCondition', NULL);
				INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (18, 16, 'UnsignedCrosswalkCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (19, 14, 'UndergroundCrosswalkCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (20, 9, 'RailwayCrossingCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (21, 20, 'SignedCrossingCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (22, 20, 'UnsignedCrossingCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (23, 9, 'RoadExtensionCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (24, 23, 'NoRoadsideCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (25, 23, 'NoSafetyBarriersCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (26, 23, 'RoadsideCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (27, 23, 'SafetyBarriersCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (28, 23, 'TreesAlongRoadCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (29, 9, 'SurfaceCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (30, 29, 'FirstCategorySurfaceCondition', NULL);
				INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (31, 30, 'SmoothCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (32, 29, 'SecondCategorySurfaceCondition', NULL);
				INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (33, 32, 'FullOfHolesCondition', NULL);
				INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (34, 32, 'FullOfWheelTracksCondition', NULL);
				INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (35, 32, 'InsufficientlySlopedCondition', NULL);
				INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (36, 32, 'PoorlyDrainedCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (37, 9, 'TrafficLighsCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (38, 37, 'BrokenLightsCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (39, 37, 'NoLightsCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (40, 37, 'WorkingLightsCondition', NULL);
			
	INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (41, 1, 'RoadInspectionCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (42, 41, 'NoInspectionCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (43, 41, 'PhotoRadarCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (44, 41, 'PoliceControlCondition', NULL);
		
	INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (45, 1, 'TimeCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (46, 45, 'DayCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (47, 45, 'DuskCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (48, 45, 'NightCondition', NULL);
		
	INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (49, 1, 'WeatherCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (50, 49, 'AirPressureCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (51, 50, 'HighPressureCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (52, 50, 'LowPressureCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (53, 49, 'AirTemperatureCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (54, 53, 'BelowFreezingTemperatureCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (55, 53, 'HighTemperatureCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (56, 53, 'LowTemperatureCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (57, 53, 'MediumTemperatureCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (58, 49, 'CloudinessCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (59, 58, 'CloudyCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (60, 58, 'SunnyCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (61, 49, 'PrecipitationCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (62, 61, 'FoggyCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (63, 61, 'NoPrecipitationCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (64, 61, 'RainyCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (65, 61, 'SnowyCondition', NULL);
		INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (66, 49, 'WindCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (67, 66, 'CrosswindCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (68, 66, 'HeadwindCondition', NULL);
			INSERT INTO traffic_condition ("id", "parent_id", "name", "description") VALUES (69, 66, 'TailwindCondition', NULL);

ALTER SEQUENCE traffic_condition_id_seq RESTART WITH 70;

INSERT INTO postal_code ("id", "value") VALUES (1, '30-079');
INSERT INTO postal_code ("id", "value") VALUES (2, '30-081');
INSERT INTO postal_code ("id", "value") VALUES (3, '30-111');
INSERT INTO postal_code ("id", "value") VALUES (4, '30-147');
INSERT INTO postal_code ("id", "value") VALUES (5, '30-319');

ALTER SEQUENCE postal_code_id_seq RESTART WITH 6;

INSERT INTO street ("id", "name") VALUES (1, 'ArmiiKrajowej');
INSERT INTO street ("id", "name") VALUES (2, 'Krolewska');
INSERT INTO street ("id", "name") VALUES (3, 'Starowislna');
INSERT INTO street ("id", "name") VALUES (4, 'Dietla');

ALTER SEQUENCE street_id_seq RESTART WITH 5;

INSERT INTO district ("id", "name") VALUES (1, 'Bronowice');
INSERT INTO district ("id", "name") VALUES (2, 'Kazimierz');
INSERT INTO district ("id", "name") VALUES (3, 'Prokocim');

ALTER SEQUENCE district_id_seq RESTART WITH 4;

INSERT INTO traffic_condition_2_postal_code (traffic_condition_id, postal_code_id) VALUES (67, 2);
INSERT INTO traffic_condition_2_postal_code (traffic_condition_id, postal_code_id) VALUES (42, 4);
INSERT INTO traffic_condition_2_postal_code (traffic_condition_id, postal_code_id) VALUES (64, 5);

INSERT INTO postal_code_2_street (street_id, postal_code_id) VALUES (2, 1);
INSERT INTO postal_code_2_street (street_id, postal_code_id) VALUES (2, 2);
INSERT INTO postal_code_2_street (street_id, postal_code_id) VALUES (1, 4);
INSERT INTO postal_code_2_street (street_id, postal_code_id) VALUES (3, 5);
INSERT INTO postal_code_2_street (street_id, postal_code_id) VALUES (4, 5);
INSERT INTO postal_code_2_street (street_id, postal_code_id) VALUES (4, 3);

INSERT INTO street_2_district (street_id, district_id) VALUES (1, 1);
INSERT INTO street_2_district (street_id, district_id) VALUES (4, 2);
INSERT INTO street_2_district (street_id, district_id) VALUES (2, 1);
INSERT INTO street_2_district (street_id, district_id) VALUES (3, 2);
