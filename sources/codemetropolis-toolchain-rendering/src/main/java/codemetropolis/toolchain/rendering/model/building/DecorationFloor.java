package codemetropolis.toolchain.rendering.model.building;

import codemetropolis.toolchain.commons.cmxml.Buildable;
import codemetropolis.toolchain.commons.cmxml.Point;
import codemetropolis.toolchain.rendering.exceptions.BuildingTypeMismatchException;
import codemetropolis.toolchain.rendering.model.BasicBlock;
import codemetropolis.toolchain.rendering.model.pattern.RepeationPattern;
import codemetropolis.toolchain.rendering.model.primitive.SolidBox;
import codemetropolis.toolchain.rendering.util.Character;
import codemetropolis.toolchain.rendering.util.Orientation;

public class DecorationFloor extends Building {

	public DecorationFloor(Buildable innerBuildable) throws BuildingTypeMismatchException {
		super(innerBuildable);
		
		prepareCeiling();
		prepareRoof();
	}
	
	protected void prepareCeiling() {
		Character externalCharacter = Character.parse(innerBuildable.getAttributeValue("external_character"));
		BasicBlock _str = externalCharacter.getBlock();
		primitives.add(
			new SolidBox(
				position.translate( new Point( center.getX() - size.getX()/2, 0, center.getZ() - size.getZ()/2 ) ),
				new Point( size.getX(), 1, size.getZ() ),
				new RepeationPattern( new BasicBlock[][][] { { { _str } } } ),
				new RepeationPattern( new BasicBlock[][][] { { { _str } } } ),
				Orientation.NearY ) );
	}
	
	private void prepareRoof() {
		BasicBlock _fire = new BasicBlock( "minecraft:fire", 51 );
		BasicBlock _iron = new BasicBlock( "minecraft:iron_bars" );
		BasicBlock _base = new BasicBlock( "minecraft:netherrack" );
		BasicBlock _air = new BasicBlock( (short) 0 );
		primitives.add(
			new SolidBox(
				position.translate( new Point( center.getX() - (5/2), 1, center.getZ() - (5/2) ) ),
				new Point( 5, 2, 5 ),
				new RepeationPattern(
					new BasicBlock[][][]
						{
							{ 
								{ _air,  _air,  _air,  _air, _air },
								{ _air, _iron, _iron, _iron, _air },
								{ _air, _iron, _fire, _iron, _air },
								{ _air, _iron, _iron, _iron, _air },
								{ _air,  _air,  _air,  _air, _air }
							},
							{ 
								{ _air,  _air,  _air,  _air, _air },
								{ _air, _base, _base, _base, _air },
								{ _air, _base, _base, _base, _air },
								{ _air, _base, _base, _base, _air },
								{ _air,  _air,  _air,  _air, _air },
						}
					} ),
				new RepeationPattern( new BasicBlock[][][] { { { new BasicBlock( (short) 0 )  } } } ),
				Orientation.NearY ) );	
	}

}
