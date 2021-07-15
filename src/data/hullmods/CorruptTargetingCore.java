package data.hullmods;

import com.fs.starfarer.api.combat.BaseHullMod;
import com.fs.starfarer.api.combat.MutableShipStatsAPI;
import com.fs.starfarer.api.combat.ShipAPI;
import com.fs.starfarer.api.combat.ShipAPI.HullSize;
import data.scripts.util.MagicIncompatibleHullmods;

public class CorruptTargetingCore extends BaseHullMod {

//	public static final float BONUS = 100f;
//	
//	public String getDescriptionParam(int index, HullSize hullSize) {
//		if (index == 0) return "" + (int)BONUS + "%";
//		return null;
//	}
//	
//	
//	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
//		stats.getBallisticWeaponRangeBonus().modifyPercent(id, BONUS);
//		stats.getEnergyWeaponRangeBonus().modifyPercent(id, BONUS);
//	}
	private static final String T_UNIT = "targetingunit";
        private static final String A_CORE = "advancedcore";
        private static final String DTC = "dedicated_targeting_core";
        private static final String CTC = "corrupttargetingcore";
        public static final float RANGE_BONUS = 90f;
	public static float PD_MINUS = 50f;
        
        @Override
	public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getBallisticWeaponRangeBonus().modifyPercent(id, RANGE_BONUS);
		stats.getEnergyWeaponRangeBonus().modifyPercent(id, RANGE_BONUS);
		
		stats.getNonBeamPDWeaponRangeBonus().modifyPercent(id, -PD_MINUS);
		stats.getBeamPDWeaponRangeBonus().modifyPercent(id, -PD_MINUS);
                if (stats.getVariant().getHullMods().contains(T_UNIT)){
            MagicIncompatibleHullmods.removeHullmodWithWarning(stats.getVariant(), T_UNIT, CTC); 
         }
                if (stats.getVariant().getHullMods().contains(A_CORE)){
            MagicIncompatibleHullmods.removeHullmodWithWarning(stats.getVariant(), A_CORE, CTC); 
         }
                if (stats.getVariant().getHullMods().contains(DTC)){
            MagicIncompatibleHullmods.removeHullmodWithWarning(stats.getVariant(), DTC, CTC); 
         }
	}

	@Override
	public boolean isApplicableToShip(ShipAPI ship) {
            return  !ship.getVariant().getHullMods().contains("targetingunit") &&
                    !ship.getVariant().getHullMods().contains("advancedcore") &&
                    !ship.getVariant().getHullMods().contains("dedicated_targeting_core");
	}
        
        
	
	@Override
	public String getUnapplicableReason(ShipAPI ship) {
                if (ship.getVariant().getHullMods().contains("targetingunit")) {
			return "Incompatible with Integrated Targeting Unit";
		}
		if (ship.getVariant().getHullMods().contains("advancedcore")) {
                        return "Incompatible with Advanced Targeting Core";
		}
		if (ship.getVariant().getHullMods().contains("dedicated_targeting_core")) {
			return "Incompatible with Dedicated Targeting Core";
		}
		
		return null;
	}
	
	
    /**
     *
     * @param index
     * @param hullSize
     * @return
     */
    @Override
	public String getDescriptionParam(int index, HullSize hullSize) {
		if (index == 0) return "" + (int)Math.round(RANGE_BONUS) + "%";
		if (index == 1) return "" + (int)Math.round(RANGE_BONUS - PD_MINUS) + "%";
		//if (index == 0) return "" + (int)RANGE_THRESHOLD;
		//if (index == 1) return "" + (int)((RANGE_MULT - 1f) * 100f);
		//if (index == 1) return "" + new Float(VISION_BONUS).intValue();
		return null;
	}
	
	
	/** public void applyEffectsBeforeShipCreation(HullSize hullSize, MutableShipStatsAPI stats, String id) {
		stats.getBallisticWeaponRangeBonus().modifyPercent(id, RANGE_BONUS);
		stats.getEnergyWeaponRangeBonus().modifyPercent(id, RANGE_BONUS);
		
		stats.getNonBeamPDWeaponRangeBonus().modifyPercent(id, -PD_MINUS);
		stats.getBeamPDWeaponRangeBonus().modifyPercent(id, -PD_MINUS);
	}**/
	
}
