Model osztály hozza létre a játéktáblát, ezen keresztül lehet azt befolyásolni és ebből lehet a megjelenítendő információt kinyerni

enterPiece függvény: egy PieceType típúsú enumot kér (ami véletlenszerűen van generálva minden meghívás esetén), ha a visszatérési értéke igaz, akkor a blokk bekerült a játéktáblára, ha hamis akkor a játék véget ért

move függvény: egy Direction típúsú enumot kér, ez lehet vagy lefele (ami bizonyos időnként automatikusan történik a kliensben) vagy jobbra/balra a játékos interakciója alapján, visszatérési értéke igaz, ha a mozgás végbe tudott menni, hamis ha nem, ha hamis eredményt kapunk a lefele mozgatás esetén, akkor az azt jelenti, hogy a blokk töbett nem mozgatható, meg kell hívni a clearRows függvényt

flip függvény: játékos gombnyomására kell meghívni, fordít 90 fokot az éppen játékba levő blokkon, visszatérési értéke hamis ha ezt nem lehet végrehajtani (valami útban van), igaz ha lehet

clearRows függvény: meg kell hívni mindig, ha a move függvény Direction.DOWN mozgás esetén hamisat adott vissza, ekkor eltakarítja a telített sorokat, lejebb mozgatja a fölötte levő sorokat és visszaadja a sorok törléséért járó pontszámot amit hozzá kell adni a játékos összpontszámához

getFields függvény: visszaad egy Field[][] típúsú tömböt, amiben a játéktér információ vannak, ebből a field[x][y].isEmpty() (ha igaz akkor üres mező, ha hamis akkor van ott valami) illetve a field[x][y].getColor() (visszaadja Color enum típúsban a mező színét) függvényekkel lehet információt kinyerni, amivel frissíteni kell a pályát minden lépés után