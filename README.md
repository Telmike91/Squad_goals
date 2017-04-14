## Tetris

### 1.1 Menürendszer
A főmenü: Új játék kezdése, Beállítások, Toplista, Kilépés

#### Új játék
A felhasználó a neve megadása után (regisztráció?) új játékot kezdhet. A játék végén megjelenik a toplista, és az aktuális felhasználó láthatja, hányadik helyet foglalja el.

#### Beállítások
* Különböző tetrominók színeinek megváltoztatása
    A felhasználó megváltoztathatja a tetrominók színeit, illetve visszaállíthatja az alapértelmezettre.
* Többszemélyes játék
* Sandbox mód
* Fogalomtár

#### Toplista
A felhasználó megtekintheti a toplistát (a játékosok elért pontszámait csökkenő sorrendben).

#### Kilépés
A program leállása.

### 1.2 A játék
A játéktábla alapértelmezett mérete 22x10 mező (ebből a 21. és 22. sorok felül rejtettek).
A tetrominok (formák) alalpértelmezett alakja és színei:
* Világoskék I (4 blokkból álló egyenes)
* Sárga O (4 blokkból álló négyzet)
* Lila T (4 blokkból álló ‘T turn’)
* Zöld S (4 blokkból álló ‘right snake’)
* Piros Z (4 blokkból álló ‘left snake’)
* Sötétkék J (4 blokkból álló J)
* Narancssárga L (4 blokkból álló fordított J/’right gun’)

A tetrominók a hosszabbik felükkel lefelé jelennek meg a pálya tetejének a közepén, illetve bal-közepén. A következő tetronimo formája véletlenszerű.
A tetronimók pozícióját a fel-le-jobbra-balra nyilakkal, vagy a WASD billentyűkkel lehet változtatni, forgatni pedig a shift billentyűkkel. Az aktuális tetronimót le lehet ejteni a space billentyűvel (hard drop).

A játékos akkor kap pontot, hogyha egy sor betelik, ekkor az a sor eltűnik és a felette levő sorok lejjebb kerülnek.
A játék véget ér, ha a legfelső, rejtett sorba kerül egy lerakott tetromino része.

### 2.	Tervezés

#### 2.1 Felhasználói-felület

**Főoldal**
![](/imgs/mockup/index.png)

**Új játék**
![](/imgs/mockup/game.png)

**Beállítások**
![](/imgs/mockup/settings.png)

**Toplista**
![](/imgs/mockup/toplist.png)

### 2.2 Adatmodell és adatbázisterv
Adatbázisban tároljuk a játékosok nevét és elért pontszámát.

### 3. Implementáció
#### 3.1 Felhasznált technológiák
- Git, Github
- Java
- (XXXsql ?)

### 4. Tesztelés


