# Testing World Income Data using Benford's Law
This is an experiment to test how many world income datasets satisfy [Benford's Law](http://en.wikipedia.org/wiki/Benfords_law). 
The dataset used comes from the [World Income Database](https://wid.world/) (WID). 

This is a simple experiment therefore the code could be bugged, and the results could contain errors.  

## Dataset and Methodology
The dataset includes all the data provided by [WID](https://wid.world/bulk_download/wid_all_data.zip) (78.1 MB), and, at the moment of 
writing, it includes 339 countries/regions. 

The .csv data files in the WID archive have all the same structure:
```
country; variable; percentile; year; value; age; pop
```

<!---
```
country;   variable;     percentile;   year;   value;       age;   pop
AE;        xlcyux999i;   p0p100;       1966;   1.9343094;   999;   i
AE;        xlcyux999i;   p0p100;       1987;   0.9862712;   999;   i
AE;        xlcusx999i;   p0p100;       1986;   3.671;       999;   i
``` 
-->
The application read a _.csv_ file and extracts the first non-zero digit under the `value` column. For example, given these 
values:  

| country | variable   | percentile | year | value     | age | pop |
| ------- | --------   | --------   | ---- | --------- | --- | --- |
| AE      | xlcyux999i | p0p100     | 1966 | **1**.9343094 | 999 | i   | 
| AE      | xlcyux999i | p0p100     | 1987 | 0.**9**862712 | 999 | i   | 
| AE      | xlcyux999i | p0p100     | 1986 | **3**.671     | 999 | i   | 
|         |            |            |      | **&#8593;**   |     |     | 

The application extracts: `1, 9, 3`.

Then it calculates the frequency for each digit. For example, given the file `WID_data_IT.csv` this is the actual digit 
distribution:

| Digit | Count | Actual Distribution  | Benford's Distribution |
| ----- | ----- | -----                | ---------------------  | 
| 1     | 39490 | 0.3089670064860382   | 0.30103                |
| 2     | 25137 | 0.19667013527575442  | 0.176091               |
| 3     | 17665 | 0.1382097282749016   | 0.124939               |
| 4     | 11788 | 0.09222849005969659  | 0.09691                |
| 5     | 7895  | 0.06176992950638824  | 0.0791812              |
| 6     | 6691  | 0.052349917457535616 | 0.0669468              |
| 7     | 6212  | 0.04860225485670472  | 0.0579919              |
| 8     | 5836  | 0.04566045707400656  | 0.0511525              |
| 9     | 7099  | 0.05554208100897405  | 0.0457575              |
|       |       |                      |                        |
| Tot:  | 127813|                      |                        |  
          
## Statistical tests 
TODO