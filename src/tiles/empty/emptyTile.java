package tiles.empty;

import Gfx.Assets;
import tiles.Tile;

public class emptyTile extends Tile {
    public emptyTile(int id) {
        super(Assets.empty, id);
    }

    @Override
    public boolean isSolid(){return false;}

}
