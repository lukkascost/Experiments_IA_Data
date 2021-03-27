using System.Collections.Generic;
using ExperimentsData.Models.DTO;

namespace ExperimentsData.Models.DAO
{
    public class ExperimentRoundEntity : BaseEntity
    {
        public DatasetEntity Dataset { get; set; }
        public List<SampleEntity> SamplesTrain;
        public List<SampleEntity> SamplesTest;
    }
}