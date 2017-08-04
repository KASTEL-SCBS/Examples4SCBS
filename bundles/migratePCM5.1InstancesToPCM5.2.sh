for d in *; do sed -i 's/\/5\.1/\/5\.2/g' $d/*.*; done
for d in *; do sed -i 's/\/5\.1/\/5\.2/g' $d/model/*.*; done