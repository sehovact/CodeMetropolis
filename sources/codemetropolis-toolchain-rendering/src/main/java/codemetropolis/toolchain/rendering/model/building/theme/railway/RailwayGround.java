package codemetropolis.toolchain.rendering.model.building.theme.railway;

import codemetropolis.toolchain.commons.cmxml.Buildable;
import codemetropolis.toolchain.commons.cmxml.Point;
import codemetropolis.toolchain.rendering.exceptions.BuildingTypeMismatchException;
import codemetropolis.toolchain.rendering.model.BasicBlock;
import codemetropolis.toolchain.rendering.model.Themes;
import codemetropolis.toolchain.rendering.model.building.Ground;
import codemetropolis.toolchain.rendering.model.pattern.RepeationPattern;
import codemetropolis.toolchain.rendering.model.primitive.SimpleBox;
import codemetropolis.toolchain.rendering.model.primitive.WallSign;
import codemetropolis.toolchain.rendering.util.Orientation;

/**
 * A {@link Ground} subclass for the {@link Themes#RAILWAY} theme.
 * Representing grounds as gates in front of the gardens..
 * 
 * @author Abigel Mester {@literal <MEAWABT.SZE>}
 */
public class RailwayGround extends Ground {

	public RailwayGround(Buildable innerBuildable) throws BuildingTypeMismatchException {
		super(innerBuildable);
	}
	
	/**
	 * Creates a gate as a representation of packages.
	 */
	@Override
	protected void prepareBase( ) {
		BasicBlock[][][] section = new BasicBlock[1][1][size.getY()];
		for(int i = 0; i < section[0][0].length; i++) {
			section[0][0][i] = RailwayBlocks.EMPTY_BLOCK;
		}
		section[0][0][0] = RailwayBlocks.EMPTY_BLOCK;
		section[0][0][size.getY() - 1] = RailwayBlocks.GATE;
		
		primitives.add(
				new SimpleBox(
					position.translate( new Point(0, size.getY(), 0) ),
					new Point( 1, 1, size.getZ() ),
					new RepeationPattern( new BasicBlock[][][]{ { { RailwayBlocks.GATE } } } ),
					Orientation.NearX ) );
		
		primitives.add(
			new SimpleBox(
				position,
				new Point( 1, size.getY(), size.getZ() ),
				new RepeationPattern( section ),
				Orientation.NearX ) );
	}
	
	/**
	 * Signs are on the top of the gates.
	 */
	@Override
	protected void prepareSigns( ) {
		primitives.add(new WallSign(position.getX(), position.getY() + size.getY(), position.getZ() + size.getZ() / 2, WallSign.Orientation.EAST, innerBuildable.getName()));
	}

}
