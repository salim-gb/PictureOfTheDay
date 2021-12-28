package com.example.pictureoftheday.repository

import com.example.pictureoftheday.model.MoonPicture

object MoonDataSource {
    fun getMoonPicture(): List<MoonPicture> {
        return listOf(
            MoonPicture(
                "https://trek.nasa.gov/tiles/Moon/EQ/LRO_WAC_Mosaic_Global_303ppd_v02/1.0.0/default/default028mm/0/0/0.jpg",
                "Apollo",
                "-67.999",
                "The Apollo 15 DEM precision is generated using the standard deviation of all the available Apollo 15 DEM files. The DEM precision units are stored as 16-bit signed integer at 14.8m/pixel resolution. The DEM units are represented in meters. These DEM precision files were generated using the Ames Stereo Pipeline: a software tool produced by the NASA Ames Intelligent Robotics Group (IRG) for automated processing of stereo imagery."
            ),
            MoonPicture(
                "https://trek.nasa.gov/tiles/Moon/EQ/LRO_WAC_Mosaic_Global_303ppd_v02/1.0.0/default/default028mm/0/0/1.jpg",
                "LRO Narrow",
                "-67.99951",
                "This shot count data file is applicable to the LRO LOLA DEM S Pole, 45 deg, version 4. This raster dataset represents a LOLA shot count for each pixel in the original polar LOLA Digital Elevation Model (DEM). Along with each gridded DEM, a image is created which reports, at the chosen resolution, how many LOLA shots intersected that pixel. If there are more than one shot per pixel, the mean of the shots is used per pixel. Where no LOLA shots are available, the pixel is approximated using a spline interpolation from the surrounding neighbors."
            ),
            MoonPicture(
                "https://trek.nasa.gov/tiles/Moon/EQ/LRO_WAC_Mosaic_Global_303ppd_v02/1.0.0/default/default028mm/1/0/0.jpg",
                "LROC NAC",
                "-67.9995117",
                "Hermite-A is one of the potential NASA Resource Prospector landing sites used to study mission design and feasibility. RP will be the first lunar surface robotic expedition to explore the character and feasibility of in situ resource utilization at the lunar poles. It is aimed at determining where, and how much, hydrogen-bearing and other volatiles are sequestered in polar cold traps. To meet its goals, the mission should land where the likelihood of finding polar volatiles is high. In addition to a high potential of volatile sequestration, a landing site candidate must meet engineering and mission operations requirements: sufficient solar access to power the rover over mission lifetime, sufficient visibility to ground stations for real time communications, manageable hazards such as slopes and block abundance, etc. A landing site must have acceptable slopes within the 3- sigma landing ellipse (200-m diameter); it should also have at least 48 hours of sun and DTE communications access to accommodate checkout, rover egress, and initial operations, with margin. RP’s Automated Traverse Search Tool has been applied to Hermite-A region, finding numerous potential traverses. This layer is a merge and crop from the North Pole NAC mosaic. It is at 1 m/pixel with Oblique Stereographic projection."
            ),
            MoonPicture(
                "https://trek.nasa.gov/tiles/Moon/EQ/LRO_WAC_Mosaic_Global_303ppd_v02/1.0.0/default/default028mm/1/0/1.jpg",
                "Apollo 15 Metric",
                "-67.999511",
                "The Apollo 15 DEM precision is generated using the standard deviation of all the available Apollo 15 DEM files. The DEM precision units are stored as 16-bit signed integer at 14.8m/pixel resolution. The DEM units are represented in meters. These DEM precision files were generated using the Ames Stereo Pipeline: a software tool produced by the NASA Ames Intelligent Robotics Group (IRG) for automated processing of stereo imagery."
            ),
            MoonPicture(
                "https://trek.nasa.gov/tiles/Moon/EQ/LRO_WAC_Mosaic_Global_303ppd_v02/1.0.0/default/default028mm/1/0/2.jpg",
                "Apollo 15 Metric",
                "-67.9995117",
                "The Apollo 15 DEM precision is generated using the standard deviation of all the available Apollo 15 DEM files. The DEM precision units are stored as 16-bit signed integer at 14.8m/pixel resolution. The DEM units are represented in meters. These DEM precision files were generated using the Ames Stereo Pipeline: a software tool produced by the NASA Ames Intelligent Robotics Group (IRG) for automated processing of stereo imagery."
            ),
            MoonPicture(
                "https://trek.nasa.gov/tiles/Moon/EQ/LRO_WAC_Mosaic_Global_303ppd_v02/1.0.0/default/default028mm/1/0/3.jpg",
                "Apollo 15 Metric Cam",
                "-67.9995117",
                "The Apollo 15 DEM precision is generated using the standard deviation of all the available Apollo 15 DEM files. The DEM precision units are stored as 16-bit signed integer at 14.8m/pixel resolution. The DEM units are represented in meters. These DEM precision files were generated using the Ames Stereo Pipeline: a software tool produced by the NASA Ames Intelligent Robotics Group (IRG) for automated processing of stereo imagery."
            ),
            MoonPicture(
                "https://trek.nasa.gov/tiles/Moon/EQ/LRO_WAC_Mosaic_Global_303ppd_v02/1.0.0/default/default028mm/1/0/4.jpg",
                "Apollo 15 Metric Cam DEM",
                "-67.9995117",
                "The Apollo 15 DEM precision is generated using the standard deviation of all the available Apollo 15 DEM files. The DEM precision units are stored as 16-bit signed integer at 14.8m/pixel resolution. The DEM units are represented in meters. These DEM precision files were generated using the Ames Stereo Pipeline: a software tool produced by the NASA Ames Intelligent Robotics Group (IRG) for automated processing of stereo imagery."
            ),
            MoonPicture(
                "https://trek.nasa.gov/tiles/Moon/EQ/LRO_WAC_Mosaic_Global_303ppd_v02/1.0.0/default/default028mm/2/0/4.jpg",
                "Apollo 15 Metric Cam",
                "-67.9995117",
                "The Apollo 15 DEM precision is generated using the standard deviation of all the available Apollo 15 DEM files. The DEM precision units are stored as 16-bit signed integer at 14.8m/pixel resolution. The DEM units are represented in meters. These DEM precision files were generated using the Ames Stereo Pipeline: a software tool produced by the NASA Ames Intelligent Robotics Group (IRG) for automated processing of stereo imagery."
            ),
            MoonPicture(
                "https://trek.nasa.gov/tiles/Moon/EQ/LRO_WAC_Mosaic_Global_303ppd_v02/1.0.0/default/default028mm/2/0/4.jpg",
                "Apollo 15 Metric Cam DEM",
                "-67.9995117",
                "The Apollo 15 DEM precision is generated using the standard deviation of all the available Apollo 15 DEM files. The DEM precision units are stored as 16-bit signed integer at 14.8m/pixel resolution. The DEM units are represented in meters. These DEM precision files were generated using the Ames Stereo Pipeline: a software tool produced by the NASA Ames Intelligent Robotics Group (IRG) for automated processing of stereo imagery."
            ),
            MoonPicture(
                "https://trek.nasa.gov/tiles/Moon/EQ/LRO_WAC_Mosaic_Global_303ppd_v02/1.0.0/default/default028mm/2/0/4.jpg",
                "Apollo 15 Metric",
                "-67.9995117",
                "The Apollo 15 DEM precision is generated using the standard deviation of all the available Apollo 15 DEM files. The DEM precision units are stored as 16-bit signed integer at 14.8m/pixel resolution. The DEM units are represented in meters. These DEM precision files were generated using the Ames Stereo Pipeline: a software tool produced by the NASA Ames Intelligent Robotics Group (IRG) for automated processing of stereo imagery."
            ),
            MoonPicture(
                "https://trek.nasa.gov/tiles/Moon/EQ/LRO_WAC_Mosaic_Global_303ppd_v02/1.0.0/default/default028mm/2/0/4.jpg",
                "Apollo 15",
                "-67.9995117",
                "The Apollo 15 DEM precision is generated using the standard deviation of all the available Apollo 15 DEM files. The DEM precision units are stored as 16-bit signed integer at 14.8m/pixel resolution. The DEM units are represented in meters. These DEM precision files were generated using the Ames Stereo Pipeline: a software tool produced by the NASA Ames Intelligent Robotics Group (IRG) for automated processing of stereo imagery."
            ),
            MoonPicture(
                "https://trek.nasa.gov/tiles/Moon/EQ/LRO_WAC_Mosaic_Global_303ppd_v02/1.0.0/default/default028mm/2/0/5.jpg",
                "Apollo 15 Metric",
                "-67.9995117",
                "The Apollo 15 DEM precision is generated using the standard deviation of all the available Apollo 15 DEM files. The DEM precision units are stored as 16-bit signed integer at 14.8m/pixel resolution. The DEM units are represented in meters. These DEM precision files were generated using the Ames Stereo Pipeline: a software tool produced by the NASA Ames Intelligent Robotics Group (IRG) for automated processing of stereo imagery."
            ),
            MoonPicture(
                "https://trek.nasa.gov/tiles/Moon/EQ/LRO_WAC_Mosaic_Global_303ppd_v02/1.0.0/default/default028mm/2/0/6.jpg",
                "Apollo 15 Metric",
                "-67.9995117",
                "The Apollo 15 DEM precision is generated using the standard deviation of all the available Apollo 15 DEM files. The DEM precision units are stored as 16-bit signed integer at 14.8m/pixel resolution. The DEM units are represented in meters. These DEM precision files were generated using the Ames Stereo Pipeline: a software tool produced by the NASA Ames Intelligent Robotics Group (IRG) for automated processing of stereo imagery."
            ),
            MoonPicture(
                "https://trek.nasa.gov/tiles/Moon/EQ/LRO_WAC_Mosaic_Global_303ppd_v02/1.0.0/default/default028mm/2/0/7.jpg",
                "Apollo 15 Metric",
                "-67.9995117",
                "The Apollo 15 DEM precision is generated using the standard deviation of all the available Apollo 15 DEM files. The DEM precision units are stored as 16-bit signed integer at 14.8m/pixel resolution. The DEM units are represented in meters. These DEM precision files were generated using the Ames Stereo Pipeline: a software tool produced by the NASA Ames Intelligent Robotics Group (IRG) for automated processing of stereo imagery."
            ),
            MoonPicture(
                "https://trek.nasa.gov/tiles/Moon/EQ/LRO_WAC_Mosaic_Global_303ppd_v02/1.0.0/default/default028mm/2/0/8.jpg",
                "Apollo 15 Metric",
                "-67.9995117",
                "The Apollo 15 DEM precision is generated using the standard deviation of all the available Apollo 15 DEM files. The DEM precision units are stored as 16-bit signed integer at 14.8m/pixel resolution. The DEM units are represented in meters. These DEM precision files were generated using the Ames Stereo Pipeline: a software tool produced by the NASA Ames Intelligent Robotics Group (IRG) for automated processing of stereo imagery."
            ),
        )
    }
}