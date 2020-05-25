using System.Collections.Generic;
using System.Linq;

namespace ExperimentsData.Models.DAO
{
    public class DatasetEntity : BaseEntity
    {
        public string description { get; set; }
        public string name { get; set; }
        public List<SampleEntity> Samples { get; set; }

        public string toFile()
        {
            var result =  Samples.OrderBy(x=>x.order).Select<SampleEntity,string>(x => x.toFile()).ToList();
            return result.Aggregate((x, old) => x + "\n" + old);
        }
    }
}