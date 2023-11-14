package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import static frc.robot.subsystems.BC_Blinkin.*;

public class SubRobotGlobals extends SubsystemBase {
    public SubRobotGlobals() {}

    @Override
    public void periodic() {
        /*
        if(game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.ConePiece) {
            blinkin.set(BLINKIN_SOLID_YELLOW);
        }
        else if(game_state.selectedPieceType == Constants.GlobalConstants.pieceTypes.CubePiece) {
            blinkin.set(BLINKIN_SOLID_VIOLET);
        }
         */
        switch(game_state.selectedPieceType) {
            case ConePiece:
                blinkin.set(BLINKIN_SOLID_YELLOW);
                break;
            case CubePiece:
                blinkin.set(BLINKIN_SOLID_VIOLET);
                break;
            case NoPiece:
                blinkin.set(BLINKIN_SOLID_RED);
                break;
        }
    }

    public Constants.GlobalConstants.game_state game_state = new Constants.GlobalConstants.game_state();

    public void setLEDColor(double color) {
        blinkin.set(color);
    }

    private final BC_Blinkin blinkin = new BC_Blinkin(0);
}
