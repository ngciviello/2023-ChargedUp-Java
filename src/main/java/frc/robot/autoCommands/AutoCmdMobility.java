package frc.robot.autoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.SubDriveTrain;

public class AutoCmdMobility extends CommandBase {
    private final SubDriveTrain subDriveTrain;
    private final Timer timer = new Timer();
    private boolean finished = false;


    public AutoCmdMobility(SubDriveTrain subDriveTrain) {
      this.subDriveTrain = subDriveTrain;
      addRequirements(subDriveTrain);
  }
    
      // Called when the command is initially scheduled.
      @Override
      public void initialize() {
        finished =false;
        timer.reset();

        System.out.println("*** auto command initialized");
      }
      // Called every time the scheduler runs while the command is scheduled.
      @Override
      public void execute() {
        timer.start();
        double speed = 0.0;
        System.out.println("*** auto command executing");

        if(!timer.hasElapsed(3)) {
        //put time to leave community here. Langth of comunity = 138.87 inches.
        System.out.println("*** auto command setting speed .6");
          speed = -0.6;
        }
        else {
        //put time to leave comunity here.
        System.out.println("*** auto command setting speed 0");
          speed = 0;
          finished = true;
        }
        subDriveTrain.drive(speed, 0);
      }
      // Called once the command ends or is interrupted.
      @Override
      public void end(boolean interrupted) {}
    
      // Returns true when the command should end.
      @Override
      public boolean isFinished() {
        return finished;
      }

}
