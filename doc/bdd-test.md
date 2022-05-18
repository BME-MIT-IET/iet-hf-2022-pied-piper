# BDD tesztek készítése

Behavior driven teszteket hoztunk létre a cucumber keretrendszer segítségével. 

Létrehoztunk összesen 8 jól működő tesztesetet, amikkel a könyvtár különböző funkcióit ellenőriztük.
Ezek voltak az alábbiak:

- Gráfok izomorfikusságának tesztelése:
  - Amiben azt vizsgáltuk, hogy ha egy adott java objektumot egymás követően megismételten sorosítunk, izomorf RDF gráfot kapunk-e eredményül
    - A két gráf izomorf lesz, a könyvtár jól működik
  - Ha két különböző objektumot sorosítunk, akkor két egymással izomorf RDF gráfot kapunk-e
    - A két gráf nem lesz izomorf, a könyvtár jól működik
- Címkék működésének tesztelése:
  - A címke az a random érték lesz-e, amit beállítottunk az elemre
    - A címke értéke megegyezik a random számmal
  - A címke értéke megegyezik-e az általunk megadott paraméterrel
    - A címke értéke megegyezik a paraméterben kapott értékkel
- Fájlból beolvasott RDF gráf vizsgálata
  - A fájlból beolvasott RDF gráf megegyezik-e az általunk kódban létrehozott gráffal
    - A két gráf megegyezik
  - A fájlból beolvasott gráf eltér-e, az általunk kódban létrehozott szándékosan különböző gráftól
    - A két gráf nem egyezik meg
- Sorosíthatatlan objektum beolvasásának tesztelése
  - Sorosíthatatlan objektum beolvasása exception-t dob-e
    - Helyes típúsú exception-t dob
  - Sorosítható objektum beolvasása nem dob exception-t
    - Nem dob exception-t
