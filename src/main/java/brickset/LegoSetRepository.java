package brickset;

import repository.Repository;
import java.util.List;

/**
 * Represents a repository of {@code LegoSet} objects.
 */
public class LegoSetRepository extends Repository<LegoSet> {

    public LegoSetRepository() {
        super(LegoSet.class, "brickset.json");
    }

    /**
     * Visszaadja egy listát (felsorolja), ami bricket nevében tartalmazza "keresett_érték" szó
     * @param keresett Keresett érték változó
     * @return LEGO [number: {number},name: {name},theme: {theme}] adatok
     */
    public List printKereses(String keresett)
    {
        String kis_keresett = keresett.toLowerCase();
        return getAll().stream()
                .filter(l -> l.getName()!=null && l.getName().toLowerCase().contains(kis_keresett))
                .map(l -> "[number: {" + l.getNumber() + "} ,name: {" +l.getName() + "} {,theme: " + l.getTheme() + "}]").toList();
    }

    /**
     * Hány darab null címke található adatbázisában?
     *
     * @return null értékű címkéje darabszámát adja vissza
     */
    public long countLegoNullsCimke()
    {
        return getAll().stream().filter(l -> l.getTags() == null).count();
    }

    /**
     * Megadjuk egy theme nevét, és megnézzük, hogy mi a legnagyobb darabszáma (pieces)...
     *
     * @return Visszaadja max értékét, ami legnagyobb darabszáma adott theme
     */
    public long GetPieceMaxWithTheme(String theme)
    {
        return getAll().stream()
                .filter(l -> l.getTheme() != null && l.getTheme().contains(theme))
                .mapToInt(LegoSet::getPieces).max().getAsInt();

    }
    public static void main(String[] args) {
        var lego = new LegoSetRepository();

        System.out.println(lego.printKereses("Obi-Wan"));
        System.out.println("'null' címke található a lego adatbázisban: " + lego.countLegoNullsCimke() +" db");
        System.out.println("[Theme = Gear] Legnagyobb darabszáma: "+ lego.GetPieceMaxWithTheme("Gear"));

    }
}
