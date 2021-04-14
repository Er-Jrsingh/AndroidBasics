DROP TABLE IF EXISTS `cities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cityname` varchar(45) DEFAULT NULL,
  `rank` int(11) DEFAULT NULL,
  `population` double DEFAULT NULL,
  `state` varchar(45) DEFAULT NULL,
  `description` varchar(4096) DEFAULT NULL,
  `image` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cities`
--

LOCK TABLES `cities` WRITE;
/*!40000 ALTER TABLE `cities` DISABLE KEYS */;
INSERT INTO `cities` VALUES 
(1, 'Korba', 1, 12064.12,'Chhattisgarh', 'Korba district was accorded the status of a full fledged revenue district with effect from 25 May, 1998. The district headquarter is Korba city, which is situated on the banks of the confluence of rivers Hasdeo and Ahiran. Korba is the power capital of Chhattisgarh. The district comes under Bilaspur division. The headquarter of Korba districts situated about 200 KM. from the capital city Raipur.', 'korba.jpg'),

(2, 'Bilaspur', 2,1650.21, 'Chhattisgarh', 'Bilaspur city is about 400 years old and the name of “Bilaspur” is named after the Fisher-woman named “Bilasa”. Despite many natural calamities, Bilaspur has developed a lot.The High Court of Chhattisgarh is located at village Bodri in district Bilaspur has privileged it with the title "Nyayadhani".The High Court of Chhattisgarh is the largest High Court of Asia. Bilaspur is the administrative headquarter of Bilaspur district.', 'Bilaspur.jpg'),

(3, 'Raipur', 3,2186.55, 'Chhattisgarh', 'Raipur being Geographically Located almost at the centre of the Chhattisgarh state, was made its capital.District Raipur is surrounded by six neighboring districts viz Durg, Bemetara, Balodabazar-Bhatapara, Mahasamund, Raipur and Dhamtari', 'Raipur.jpg'),

(4, 'Bhilai', 4,10642.1, 'Chhattisgarh', 'Bhilai is a city in the Indian state of Chhattisgarh, in eastern central India. With a population of 1,064,077, Bhilai–Durg is the second largest area after Raipur in the Indian state of Chhattisgarh. Bhilai is a major industrial city in India as well as an education hub of central India.Bhilai was a small village and a part of the Haihaiyavansi Kingdom until 1740, when it was conquered by the Marathas. It was administered by the British from 1853 until 1947, when it merged with the Indian union', 'Bhilai.jpg'),

(5, 'Bengaluru', 5, 10600.20,'Karnataka', 'Bengaluru is the capital and the largest city of the Indian state of Karnataka. It has a population of more than 8 million and a metropolitan population of around 11 million, making it the third most populous city and fifth most populous urban agglomeration in India. Located in southern India on the Deccan Plateau, at a height of over 900 m (3,000 ft) above sea level, Bangalore is known for its pleasant climate throughout the year. Its elevation is the highest among the major cities of India.', 'Bengaluru.jpg'),

(6, 'Ahmedabad ', 6,19045.22, 'Gujarat', 'Ahmedabad  is the largest city and former capital of the Indian state of Gujarat. It is the administrative headquarters of the Ahmedabad district and the seat of the Gujarat High Court. Ahmedabad population of 5,633,927 makes it the fifth-most populous city in India,and the encompassing urban agglomeration population estimated at 6,357,693 is the seventh-most populous in India.Ahmedabad is located on the banks of the Sabarmati River, 23 km (14 mi) from the state capital Gandhinagar, which is its twin city','Ahmedabad.jpg'),

(7, 'Mumbai', 7, 184542.88,'Maharashtra', 'Mumbai  is the capital city of the Indian state of Maharashtra. According to the United Nations, as of 2018, Mumbai is the second-most populous city in the country after Delhi and the seventh-most populous city in the world with a population of roughly 20 million. As per Indian government population census of 2011, Mumbai was the most populous city in India with an estimated city proper population of 12.5 million living under Municipal Corporation of Greater Mumbai. Mumbai is the centre of the Mumbai Metropolitan Region, the sixth most populous metropolitan area in the world with a population of over 23 million.', 'Mumbai.jpg'),

(8, 'Hyderabad', 8, 9445.00,'Telangana', 'Hyderabad is the capital and largest city of the Indian state of Telangana and the de facto capital of Andhra Pradesh. It occupies 625 square kilometres (241 sq mi) on the Deccan Plateau along the banks of the Musi River, in the northern part of South India', 'Hyderabad.jpg'),

(9, 'Chennai', 9, 8917.4, 'Tamilnadu', 'Chennai also known as Madras,the official name until 1996 is the capital of the Indian state of Tamil Nadu. Located on the Coromandel Coast of the Bay of Bengal, it is one of the largest cultural, economic and educational centres of south India. According to the 2011 Indian census, it is the sixth-most populous city and fourth-most populous urban agglomeration in India. The city together with the adjoining regions constitutes the Chennai Metropolitan Area, which is the 36th-largest urban area by population in the world.', 'Chennai.jpg'),

(10, 'Delhi', 10, 1000.5, 'Delhi', 'Delhi, city and national capital territory, north-central India. The city of Delhi actually consists of two components: Old Delhi, in the north, the historic city; and New Delhi, in the south, since 1947 the capital of India, built in the first part of the 20th century as the capital of British India.', 'Delhi.jpg'),

(11, 'Chittorgarh', 11, 7600.21,'Rajasthan', 'The District  of Rajasthan, Chittorgarh is located on the banks of river Gambhiri and Berach( a Tributary of Banas). The history of this town is written in Blood and Sacrifice .Chittorgarh, the garh(fort) at chittor, is the greatest in India, and is well worth reshuffling an itinerary to explore. It rise from the plains like a huge rock island,nearly 6 Km long and surrounded on all sides by 150m plus cliffs.It is a World Heritage Site.The Great Maharana Pratap,son of Rana Udai Singh II, is Regarded as a person a personification of the values Rajputs cherish and die for.', 'Chittorgarh.jpg'),

(12, 'Kolkata', 12, 659.05,'West Bengal', 'Kolkata, Bengali Kalikata, formerly Calcutta, city, capital of West Bengal state, and former capital (1772–1911) of British India. It is one of India’s largest cities and one of its major ports. The city is centred on the east bank of the Hugli (Hooghly) River, once the main channel of the Ganges (Ganga) River, about 96 miles (154 km) upstream from the head of the Bay of Bengal; there the port city developed as a point of transshipment from water to land and from river to sea. A city of commerce, transport, and manufacture, Kolkata is the dominant urban centre of eastern India', 'Kolkata.jpg'),

(13, 'Chandigarh', 13, 6598.25,'Punjab', 'Chandigarh, the dream city of Indias first Prime Minister, Sh. Jawahar Lal Nehru, was planned by the famous French architect Le Corbusier. Picturesquely located at the foothills of Shivaliks, it is known as one of the best experiments in urban planning and modern architecture in the twentieth century in India.', 'Chandigarh.jpg');

/*!40000 ALTER TABLE `cities` ENABLE KEYS */;
UNLOCK TABLES;
-- /*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

-- /*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
-- /*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
-- /*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
-- /*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
-- /*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
-- /*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
-- /*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

