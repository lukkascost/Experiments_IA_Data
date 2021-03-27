using System;
using System.Collections.Generic;

namespace ExperimentsData.Models.DTO
{
    public class ExperimentRoundDTO
    {
        public Guid DatasetId { get; set; }
        public List<SampleRegisterDTO> samplesTrain;
        public List<SampleRegisterDTO> samplesTest;
    }
}