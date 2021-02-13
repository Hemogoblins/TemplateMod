/*
 * Copyright (C) 2021 Hemogoblins
 * This file is part of TemplateMod.
 *
 * TemplateMod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TemplateMod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with TemplateMod.  If not, see <https://www.gnu.org/licenses/>.
 */
package com.hemogoblins.templatemod;

import com.hemogoblins.templatemod.proxy.ClientProxy;
import com.hemogoblins.templatemod.proxy.CommonProxy;
import com.hemogoblins.templatemod.proxy.Proxy;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TemplateMod.MOD_ID)
public class TemplateMod {

    public static final String MOD_ID = "templatemod";

    public static Proxy proxy;

    public static final Logger LOGGER = LogManager.getLogger(MOD_ID);

    public TemplateMod() {
        //noinspection deprecation
        DistExecutor.callWhenOn(Dist.CLIENT, () -> () -> proxy = new ClientProxy());
        //noinspection deprecation
        DistExecutor.callWhenOn(Dist.DEDICATED_SERVER, () -> () -> proxy = new CommonProxy());

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.COMMON_SPEC);

        proxy.onConstruct();
    }
}
