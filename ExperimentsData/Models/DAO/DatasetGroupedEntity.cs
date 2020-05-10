using System.Linq;

namespace ExperimentsData.Models.DAO
{
    public class DatasetGroupedEntity : BaseEntity
    {
        public string description { get; set; }
        public string name { get; set; }
        public long Samples { get; set; }
        public DatasetGroupedEntity(DatasetEntity datasetEntity)
        {
            this.description = datasetEntity.description;
            this.name = datasetEntity.name;
            this.Samples = datasetEntity.Samples.Count();
            base.Id = datasetEntity.Id;
        }

        public DatasetGroupedEntity(dynamic datasetEntity)
        {
            this.description = datasetEntity.description;
            this.name = datasetEntity.name;
            this.Samples = datasetEntity.Samples;
            base.Id = datasetEntity.Id;

        }
    }
}