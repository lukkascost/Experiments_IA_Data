using System.Collections.Generic;

namespace ExperimentsData.Models.DAO
{
    public class DatasetEntity : BaseEntity
    {
        public string description { get; set; }
        public string name { get; set; }
        public List<SampleEntity> Samples { get; set; }
    }
}