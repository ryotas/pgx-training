#!/bin/bash
sort -t ',' -k 1 station20180330free.csv > station.csv
sort -t ',' -k 1 line20180424free.csv > line.csv
sort -t ',' -k 2 join20180330.csv > join01.csv
join -t ',' -1 2 -2 1 -o 1.1,2.2,1.3 join01.csv station.csv > join02.csv
sort -t ',' -k 3 join02.csv > join03.csv
join -t ',' -1 3 -2 1 -o 1.1,1.2,2.2 join03.csv station.csv > join04.csv
sort -t ',' -k 1 join04.csv > join05.csv
join -t ',' -1 1 -2 1 -o 2.3,1.2,1.3 join05.csv line.csv > join06.csv
cat station.csv | awk -v FS=',' -v OFS=',' '{if (NR != 1) print $2}' | sort | uniq > station_uniq.csv
join -t ',' -1 1 -2 1 -o 1.1,2.3 station_uniq.csv station.csv > join07.csv
cat join07.csv | awk -v FS=',' -v OFS=',' '{print $1, ":station", "name:\""$2"\""}' > rail.pg
cat join06.csv | awk -v FS=',' -v OFS=',' '{print $2, "->", $3, ":rail", "name:\""$1"\"", "score:1"}' >> rail.pg
sed s/,/' '/g rail.pg > rail2.pg
rm join0*.csv station.csv line.csv station_uniq.csv
