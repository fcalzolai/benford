# Testing World Income Data using Benford's Law
This is an experiment to test how many world income datasets satisfy [Benford's Law](http://en.wikipedia.org/wiki/Benfords_law). 
The dataset used comes from the [World Income Database](https://wid.world/) (WID). The application calculates the digits 
distribution for each dataset file provided by WID and then it applies several statistical tests to determine if the 
actual distribution adhere to the Benford's law.

Since this is a simple experiment, the code may be bugged, and the results may contain errors. 

## TL;DR 
There are 4 countries (out of 337 economic zones) that adhere to Benford's law:
 * Turks and Caicos Islands 
 * Liechtenstein
 * Solomon Islands
 * Costa Rica

Jump to [Results](#results) or [Best and Worst fit](#best-and-worst-fit) sections.   


## Dataset and Methodology
The dataset includes all the data provided by [WID](https://wid.world/bulk_download/wid_all_data.zip) (78.1 MB), and, at the moment of 
writing, it includes 337 countries/regions. 

The .csv data files in the WID archive have all the same structure:
```
country; variable; percentile; year; value; age; pop
```

The application read a _.csv_ file and extracts all the first non-zero digit under the `value` column. For example, 
given these values:  

| country | variable   | percentile | year | value     | age | pop |
| ------- | --------   | --------   | ---- | --------- | --- | --- |
| AE      | xlcyux999i | p0p100     | 1966 | **1**.9343094 | 999 | i   | 
| AE      | xlcyux999i | p0p100     | 1987 | 0.**9**862712 | 999 | i   | 
| AE      | xlcyux999i | p0p100     | 1986 | **3**.671     | 999 | i   | 
|         |            |            |      | **&#8593;**   |     |     | 

It extracts: `1, 9, 3`.

For each dataset, the application calculates the frequency of each digit and then some statistical tests. For example, 
given the file [WID_data_IT.csv](./src/main/resources/stats/WID_data_IT.csv) this is the result 
([Results_WID_data_IT.csv](./results/Results_WID_data_IT.csv)):

| Digit | Count | Actual Distribution  | Benford's Distribution | ZScore | Chi Square |
| ----- | ----- | -----                | ---------------------  | ------ | ---------- |
|1|39490.0|0.3089670064860382|0.30103|6.182953577971043|26.747224347567954|
|2|25137.0|0.19667013527575442|0.176091|19.311849508810653|307.3916830621211|
|3|17665.0|0.1382097282749016|0.124939|14.344521087967902|180.16337827507854|
|4|11788.0|0.09222849005969659|0.09691|5.652760608534583|28.905356815636882|
|5|7895.0|0.06176992950638824|0.0791812|23.047417031360148|489.3435570337638|
|6|6691.0|0.052349917457535616|0.0669468|20.874356986123594|406.7854704845627|
|7|6212.0|0.04860225485670472|0.0579919|14.3563729047296|194.31487605787416|
|8|5836.0|0.04566045707400656|0.0511525|8.905951785913597|75.36609452096197|
|9|7099.0|0.05554208100897405|0.0457575|16.733837839196273|267.42204569607225|
|       |       |                      |                        |  |  |
| Tot:  | 127813|                      |                        |  |  |

where:
 * `Count` is the number of occurrences of a digit.  
 * `Actual Distribution` the distribution for the specified digit, i.e. for digit `1`: 
     ```
     39490/127813 = 0.3089670064860382 ~ 30.89% 
    ```
 * `Benford's Distribution` is the expected distribution value following the Benford's law for the first digit.
 * `ZScore` is the ZScore value of a digit.
 * `Chi Square` is the chi-square value of a digit.
 
`ZScore` and `Chi Square` values can be used to determine if the input values might be fudged or corrupted. 
          
          
## Statistical tests 
We used different statistical tests for verifying Benford's law:
* **ZScore**: used to verify single digit hypothesis: "_The digit `d` (where `d` is in `{1, ... 9}`) adhere to Benford's law_".
* **Chi-Square**: used to verify digit distribution hypothesis:  "_All the digits adhere to Benford's law_".

### ZScore
For more on ZScore, please visit [this Wiki page](https://en.wikipedia.org/wiki/Standard_score).  

### Chi-Square
For more on Chi-Square, please visti [this Wiki page](https://en.wikipedia.org/wiki/Chi-square_distribution).


## Results
In order to have an overall view of the generated results, the application generates a summary file called [Result_Summary.csv](./results/Results_Summary.csv)
where it lists the statistical test results for each dataset file. For example:

| Source file | Country | # Lines  | # ZScore digits (&alpha;=0.05) | # ZScore digits (&alpha;=0.01) | P Value |
| ----- | ----- | ----- | ---------------------  | ------ | ---------- |
|WID_data_QJ.csv|"Central Asia"|3394|4|4|0|
|WID_data_MG.csv|"Madagascar"|44322|6|6|0|
|WID_data_MN.csv|"Mongolia"|8814|3|1|0.004184690723312|

where:
* `# Lines` is the number of lines in the corresponding input file
* `# ZScore digit (&alpha;=0.05)` is the number of digit (`{1 ... 9}`) that do not adhere to the Benford's law with a level of
  significance of 5%.
* `# ZScore digit (&alpha;=0.01)` is the number of digit (`{1 ... 9}`) that do not adhere to the Benford's law with a level of
  significance of 1%.
* `P Value` is the p value calculated using chi-square values for all the digits.

For each input file, the application generates an output file containing the digit distribution and statistical analysis
results. For example, in the table below you can see the source file, and the corresponding result file:  

| Source File | Result File |
| ----------  | ----------- |
| [WID_data_IT.csv](./src/main/resources/stats/WID_data_IT.csv) | [Results_WID_data_IT.csv](./results/Results_WID_data_IT.csv) |
| [WID_data_FR.csv](./src/main/resources/stats/WID_data_FR.csv) | [Results_WID_data_FR.csv](./results/Results_WID_data_FR.csv) |
| [WID_data_GB.csv](./src/main/resources/stats/WID_data_GB.csv) | [Results_WID_data_GB.csv](./results/Results_WID_data_GB.csv) |

[This](./src/main/resources/stats) is the directory containing all the source datasets, and [this](./results) is the 
directory containing all the results files.

## Best and worst fit
The statistical fit depends on which test you chose, but here below a small summary for best and worst fit using the 
tests provided. 

### Best and Worst Z-Score fit
The ZScore digit value is the number of digit (`{1 ... 9}`) that do not adhere to the Benford's law with a level of
significance of 5% (or 1%). In order to obtain these rankings, the results has been ordered by:
1. Z-Score (&alpha;=0.5) 
2. Z-Score (&alpha;=0.01) 
3. Number of Lines

| Place | Country                  | # Lines | Z-Score (5%) | Z-Score (1%) |
| ----- | -----                    | -----   | -----        | -----   |
| 1     | Turks and Caicos Islands | 645     | 0            | 0       | 
| 2     | Solomon Islands          | 8544    | 1            | 0       | 
| 3     | Costa Rica               | 9022    | 1            | 0       | 
| 3     | Nauru                    | 767     | 1            | 0       | 
| 3     | American Samoa           | 123     | 1            | 1       | 
| 3     | Greenland                | 219     | 1            | 1       | 
| ...   | ...                      | ...     | ...          | ...     |   
| 334   | Italy                    | 127813  | 9            | 9       |        
| 335   | Germany                  | 130144  | 9            | 9       |        
| 336   | Czech Republic           | 143982  | 9            | 9       |         
| 337   | France                   | 895904  | 9            | 9       |        


### Best and Worst P-value fit
In order to obtain these rankings, the results has been ordered by:
1. P-value 
2. Number of Lines

| Place | Country                  | # Lines | P value | 
| ----- | -----                    | -----   | -----   |  
| 1     | Turks and Caicos Islands | 645     | 0.278177159275576 | 
| 2     | Liechtenstein            | 327     | 0.088474505876344 |
| 3     | Solomon Islands          | 8544    | 0.080630695165187 | 
| ...   | ...                      | ...     | ...               |        
| 335   | United Kingdom           | 130144  | 0                 |        
| 336   | USA                      | 143982  | 0                 |        
| 337   | France                   | 895904  | 0                 |    




   